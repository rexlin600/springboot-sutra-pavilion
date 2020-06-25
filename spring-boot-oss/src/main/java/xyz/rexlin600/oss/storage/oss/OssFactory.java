package xyz.rexlin600.oss.storage.oss;

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
import xyz.rexlin600.oss.storage.StorageService;

/**
 * OSS 工厂类
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Slf4j
@Component
public class OssFactory implements BeanFactoryAware {

    /**
     * 阿里、腾讯、七牛配置类
     */
    private AliOssConfig aliOssConfig;
    private TxOssConfig txOssConfig;
    private QnOssConfig qnOssConfig;

    /**
     * 存在相应配置才注入相应 SVC
     *
     * @param b
     * @throws BeansException
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
     * 创建存储对象
     *
     * @return 抽象存储对象
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
     * 获取OSS服务类
     *
     * @param ossType
     * @return
     */
    private StorageService getStorageService(Integer ossType) {
        StorageService storageService;
        switch (OSSTypeEnum.get(ossType)) {
            case ALI:
                storageService = new AliStorageService(aliOssConfig);
                break;
            case TX:
                storageService = new TxStorageService(txOssConfig);
                break;
            case QN:
                storageService = new QnStorageService(qnOssConfig);
                break;
            default:
                throw new RuntimeException("不支持的OSS类型");
        }
        return storageService;
    }

    /**
     * 检查是否启用相应 OSS 配置
     *
     * @param ossType
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
                throw new RuntimeException("不支持的OSS类型");
        }
    }

}