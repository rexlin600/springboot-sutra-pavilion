package xyz.rexlin600.oss.storage;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import xyz.rexlin600.oss.common.OssConstant;
import xyz.rexlin600.oss.config.AliOssConfig;
import xyz.rexlin600.oss.util.PathUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Ali storage service
 *
 * @author hekunlin
 */
@ConditionalOnBean(AliOssConfig.class)
@Service
class AliStorageServiceImpl implements StorageService {

	/**
	 * Config
	 */
	private final AliOssConfig config;
	/**
	 * Client
	 */
	private OSS client;

	/**
	 * Ali storage service
	 *
	 * @param config config
	 */
	@Autowired
	public AliStorageServiceImpl(AliOssConfig config) {
		this.config = config;
		//初始化
		init();
	}

	/**
	 * Init
	 */
	private void init() {
		client = new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKey(), config.getAccessKeySecret());
	}

	// -----------------------------------------------------------------------------------------------
	// 实用方法
	// -----------------------------------------------------------------------------------------------

	/**
	 * Upload string
	 *
	 * @param data     data
	 * @param fileName file name
	 * @param path     path
	 * @return the string
	 */
	@Override
	public String upload(byte[] data, String fileName, String path) {
		return upload(new ByteArrayInputStream(data), fileName, path);
	}

	/**
	 * Upload string
	 *
	 * @param inputStream input stream
	 * @param fileName    file name
	 * @param path        path
	 * @return the string
	 */
	@Override
	public String upload(InputStream inputStream, String fileName, String path) {
		path = PathUtil.resolvePath(path, fileName);

		try {
			client.putObject(config.getBucketName(), path, inputStream);
		} catch (OSSException ex) {
			throw new OSSException("OSS异常，异常码=" + ex.getErrorCode() + " 异常信息=" + ex.getErrorMessage());
		} catch (ClientException ex) {
			throw new OSSException("OSS客户端异常，异常码=" + ex.getErrorCode() + " 异常信息=" + ex.getErrorMessage());
		}

		return config.getDomain().concat(OssConstant.SLASH).concat(path);
	}

	/**
	 * Download input stream
	 *
	 * @param key key
	 * @return the input stream
	 */
	@Override
	public InputStream download(String key) {
		OSSObject object = client.getObject(new GetObjectRequest(this.config.getBucketName(), key));
		return object.getObjectContent();
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

		// 创建文件
		PathUtil.createFile(path);

		client.getObject(new GetObjectRequest(config.getBucketName(), key), new File(path));
	}

	/**
	 * Delete *
	 *
	 * @param key key
	 */
	@Override
	public void delete(String key) {
		client.deleteObject(config.getBucketName(), key);
	}

}