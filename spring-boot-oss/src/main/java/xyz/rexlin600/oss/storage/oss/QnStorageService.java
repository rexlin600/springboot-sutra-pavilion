package xyz.rexlin600.oss.storage.oss;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import xyz.rexlin600.oss.config.QnOssConfig;
import xyz.rexlin600.oss.storage.StorageService;
import xyz.rexlin600.oss.util.PathUtil;

import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 七牛云存储服务
 *
 * @author: hekunlin
 * @date: 2020/6/22
 */
@SuppressWarnings("DuplicatedCode")
@ConditionalOnBean(QnOssConfig.class)
@Service
public class QnStorageService implements StorageService {

    private UploadManager uploadManager;
    private String token;
    private Auth auth;
    private BucketManager bucketManager;

    /**
     * 腾讯云配置
     */
    private final QnOssConfig config;

    public QnStorageService(QnOssConfig config) {
        this.config = config;
        //初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        Configuration cfg = new Configuration(Region.autoRegion()); // 可以替换为明确的 region
        uploadManager = new UploadManager(cfg);
        auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        token = auth.uploadToken(config.getBucketName());
        bucketManager = new BucketManager(auth, cfg);
    }

    @Override
    public String upload(byte[] data, String fileName, String path) throws IOException {
        path = PathUtil.resolvePath(path, fileName);

        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new IOException("上传文件失败，请核对七牛配置信息");
        }

        return config.getDomain().concat(path);
    }

    @Override
    public String upload(InputStream inputStream, String fileName, String path) throws IOException {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, fileName, path);
        } catch (IOException e) {
            throw new IOException("上传文件失败");
        }
    }

    @Override
    public InputStream download(String key) throws IOException {
        // 拼接 key
        key = String.format("%s/%s", config.getDomain(),
                URLEncoder.encode(key, "utf-8").replace("+", "%20"));

        // 下载私有资源
        //long expireInSeconds = 3600; //1小时，可以自定义链接过期时间
        //String downloadUrl = auth.privateDownloadUrl(key, expireInSeconds);

        // 下载公开资源
        String downloadUrl = key;

        BufferedInputStream bufferedInputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(downloadUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("七牛云下载失败");
        }

        return bufferedInputStream;
    }

    @Override
    public void download(String key, String path) throws IOException {
        InputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = this.download(key);

            // 创建文件
            File file = PathUtil.createFile(path);

            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[8192];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("七牛云下载异常");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    public void delete(String key) throws IOException {
        try {
            bucketManager.delete(config.getBucketName(), key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            throw new IOException("删除文件文件失败");
        }
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

}