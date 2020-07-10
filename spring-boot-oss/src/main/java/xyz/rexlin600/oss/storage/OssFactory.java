package xyz.rexlin600.oss.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import xyz.rexlin600.oss.common.OssConstant;
import xyz.rexlin600.oss.config.AliOssConfig;
import xyz.rexlin600.oss.config.QnOssConfig;
import xyz.rexlin600.oss.config.TxOssConfig;
import xyz.rexlin600.oss.enums.OSSTypeEnum;

/**
 * Oss factory
 *
 * @author hekunlin
 */
@Slf4j
@Component
public class OssFactory implements BeanFactoryAware {

	/**
	 * Ali oss config
	 */
	private AliOssConfig aliOssConfig;
	/**
	 * Tx oss config
	 */
	private TxOssConfig txOssConfig;
	/**
	 * Qn oss config
	 */
	private QnOssConfig qnOssConfig;

	/**
	 * Sets bean factory *
	 *
	 * @param b b
	 * @throws BeansException beans exception
	 */
	@Override
	public void setBeanFactory(BeanFactory b) throws BeansException {
		boolean enable = false;
		if (b.containsBean(OssConstant.ALI_CONFIG)) {
			this.aliOssConfig = b.getBean(AliOssConfig.class);
			log.info("==>  启用阿里云OSS对象存储服务");
			enable = true;
		}
		if (b.containsBean(OssConstant.TX_CONFIG)) {
			this.txOssConfig = b.getBean(TxOssConfig.class);
			log.info("==>  启用腾讯云OSS对象存储服务");
			enable = true;
		}
		if (b.containsBean(OssConstant.QN_CONFIG)) {
			this.qnOssConfig = b.getBean(QnOssConfig.class);
			log.info("==>  启用七牛云OSS对象存储服务");
			enable = true;
		}

		if (!enable) {
			log.info("==>  未启用OSS服务 ...");
		}
	}

	/**
	 * Build storage service
	 *
	 * @param ossType oss type
	 * @return the storage service
	 */
	public StorageService build(Integer ossType) {
		StorageService storageService;

		// 检查
		checkConfig(ossType);

		// 根据不同的 OSS 类型创建不同的 OSS 实现类
		storageService = getStorageService(ossType);

		return storageService;
	}

	/**
	 * Gets storage service *
	 *
	 * @param ossType oss type
	 * @return the storage service
	 */
	private StorageService getStorageService(Integer ossType) {
		StorageService storageService;
		switch (OSSTypeEnum.get(ossType)) {
			case ALI:
				storageService = new AliStorageServiceImpl(aliOssConfig);
				break;
			case TX:
				storageService = new TxStorageServiceImpl(txOssConfig);
				break;
			case QN:
				storageService = new QnStorageServiceImpl(qnOssConfig);
				break;
			default:
				throw new UnsupportedOperationException("不支持的OSS类型");
		}
		return storageService;
	}

	/**
	 * Check config *
	 *
	 * @param ossType oss type
	 */
	private void checkConfig(Integer ossType) {
		switch (OSSTypeEnum.get(ossType)) {
			case ALI:
				if (ObjectUtils.isEmpty(aliOssConfig)) {
					throw new UnsupportedOperationException("未启用阿里云OSS服务");
				}
				break;
			case TX:
				if (ObjectUtils.isEmpty(txOssConfig)) {
					throw new UnsupportedOperationException("未启用腾讯云OSS服务");
				}
				break;
			case QN:
				if (ObjectUtils.isEmpty(qnOssConfig)) {
					throw new UnsupportedOperationException("未启用七牛云OSS服务");
				}
				break;
			default:
				throw new UnsupportedOperationException("不支持的OSS类型");
		}
	}

}