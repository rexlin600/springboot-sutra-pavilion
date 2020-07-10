package xyz.rexlin600.oss.storage;

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
import xyz.rexlin600.oss.util.PathUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Qn storage service
 *
 * @author hekunlin
 */
@SuppressWarnings("DuplicatedCode")
@ConditionalOnBean(QnOssConfig.class)
@Service
class QnStorageServiceImpl implements StorageService {

	/**
	 * Config
	 */
	private final QnOssConfig config;
	/**
	 * Upload manager
	 */
	private UploadManager uploadManager;
	/**
	 * Token
	 */
	private String token;
	/**
	 * Bucket manager
	 */
	private BucketManager bucketManager;

	/**
	 * Qn storage service
	 *
	 * @param config config
	 */
	public QnStorageServiceImpl(QnOssConfig config) {
		this.config = config;
		//初始化
		init();
	}

	/**
	 * Init
	 */
	private void init() {
		// 可以替换为明确的 region
		Configuration cfg = new Configuration(Region.autoRegion());
		uploadManager = new UploadManager(cfg);
		Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		token = auth.uploadToken(config.getBucketName());
		bucketManager = new BucketManager(auth, cfg);
	}

	/**
	 * Upload string
	 *
	 * @param data     data
	 * @param fileName file name
	 * @param path     path
	 * @return the string
	 * @throws IOException io exception
	 */
	@Override
	public String upload(byte[] data, String fileName, String path) throws IOException {
		path = PathUtil.resolvePath(path, fileName);

		try {
			Response res = uploadManager.put(data, path, token);
			if (!res.isOK()) {
				throw new IOException("上传七牛出错：" + res.toString());
			}
		} catch (QiniuException e) {
			e.printStackTrace();
			throw new IOException("上传文件失败，请核对七牛配置信息");
		}

		return config.getDomain().concat(path);
	}

	/**
	 * Upload string
	 *
	 * @param inputStream input stream
	 * @param fileName    file name
	 * @param path        path
	 * @return the string
	 * @throws IOException io exception
	 */
	@Override
	public String upload(InputStream inputStream, String fileName, String path) throws IOException {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			return this.upload(data, fileName, path);
		} catch (IOException e) {
			throw new IOException("上传文件失败");
		}
	}

	/**
	 * Download input stream
	 *
	 * @param key key
	 * @return the input stream
	 * @throws IOException io exception
	 */
	@Override
	public InputStream download(String key) throws IOException {
		// 拼接 key
		key = String.format("%s/%s", config.getDomain(),
				URLEncoder.encode(key, "utf-8").replace("+", "%20"));

		// 下载公开资源
		String downloadUrl = key;
		BufferedInputStream bufferedInputStream;
		HttpURLConnection connection;
		try {
			URL url = new URL(downloadUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(1000 * 5);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();

			bufferedInputStream = new BufferedInputStream(connection.getInputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IOException("七牛云下载失败");
		}

		return bufferedInputStream;
	}

	/**
	 * Download *
	 *
	 * @param key  key
	 * @param path path
	 * @throws IOException io exception
	 */
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

	/**
	 * Delete *
	 *
	 * @param key key
	 * @throws IOException io exception
	 */
	@Override
	public void delete(String key) throws IOException {
		try {
			bucketManager.delete(config.getBucketName(), key);
		} catch (QiniuException ex) {
			//如果遇到异常，说明删除失败
			throw new IOException("删除文件文件失败");
		}
	}

}