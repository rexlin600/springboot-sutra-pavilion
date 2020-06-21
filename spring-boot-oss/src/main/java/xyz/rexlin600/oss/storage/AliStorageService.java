package xyz.rexlin600.oss.storage;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.rexlin600.oss.config.AliOssConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储服务实现类
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Service
public class AliStorageService extends AbstractStorageService {

    private OSS client;

    /**
     * 阿里云配置
     */
    private AliOssConfig config;

    public AliStorageService(AliOssConfig config) {
        this.config = config;
        //初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        client = new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKey(), config.getAccessKeySecret());
    }

    // -----------------------------------------------------------------------------------------------
    // 实用方法
    // -----------------------------------------------------------------------------------------------

    @Override
    public String upload(byte[] data, String fileName, String path) {
        return upload(new ByteArrayInputStream(data), fileName, path);
    }

    @Override
    public String upload(InputStream inputStream, String fileName, String path) {
        // 处理 path
        path = path.replaceAll("/+", "/");
        if (StringUtils.isEmpty(path) || path.equals("/")) {
            path = fileName;
        } else {
            if (path.startsWith("/")) { // 注：阿里云不支持 path 开头为 /
                path = path.substring(1);
            }
            if (path.endsWith("/")) {
                path = path.concat(fileName);
            } else {
                path = path.concat("/").concat(fileName);
            }
        }

        try {
            client.putObject(config.getBucketName(), path, inputStream);
        } catch (OSSException ex) {
            throw new RuntimeException("OSS异常，异常码=" + ex.getErrorCode() + " 异常信息=" + ex.getErrorMessage());
        } catch (ClientException ex) {
            throw new RuntimeException("OSS客户端异常，异常码=" + ex.getErrorCode() + " 异常信息=" + ex.getErrorMessage());
        }

        return config.getDomain().concat("/").concat(path);
    }

    @Override
    public InputStream download(String key) {
        OSSObject object = client.getObject(new GetObjectRequest(this.config.getBucketName(), key));
        return object.getObjectContent();
    }

}