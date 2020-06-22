package xyz.rexlin600.oss.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.rexlin600.oss.config.AliOssConfig;
import xyz.rexlin600.oss.config.QnOssConfig;
import xyz.rexlin600.oss.config.TxOssConfig;
import xyz.rexlin600.oss.enums.OSSTypeEnum;
import xyz.rexlin600.oss.storage.oss.AliStorageService;
import xyz.rexlin600.oss.storage.oss.QnStorageService;
import xyz.rexlin600.oss.storage.oss.TxStorageService;

import java.time.Instant;

/**
 * OSS 工厂类
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Slf4j
@Component
public class OssFactory {

    /**
     * 阿里云、腾讯云、七牛云 配置类
     */
    private final AliOssConfig aliConfig;
    private final TxOssConfig txOssConfig;
    private final QnOssConfig qnOssConfig;

    @Autowired
    public OssFactory(AliOssConfig aliConfig,
                      TxOssConfig txOssConfig,
                      QnOssConfig qnOssConfig) {
        this.aliConfig = aliConfig;
        this.txOssConfig = txOssConfig;
        this.qnOssConfig = qnOssConfig;
    }

    /**
     * 创建存储对象
     *
     * @return 抽象存储对象
     */
    public StorageService build(Integer ossType) {
        StorageService storageService = null;

        /**
         * 根据不同的 OSS 类型创建不同的 OSS 实现类
         */
        switch (OSSTypeEnum.get(ossType)) {
            case ALI:
                log.info("==>  感谢使用阿里云OSS {}", Instant.now().toEpochMilli());
                storageService = new AliStorageService(aliConfig);
                break;
            case TX:
                storageService = new TxStorageService(txOssConfig);
                log.info("==>  感谢使用腾讯云OSS {}", Instant.now().toEpochMilli());
                break;
            case QN:
                storageService = new QnStorageService(qnOssConfig);
                log.info("==>  感谢使用七牛云OSS {}", Instant.now().toEpochMilli());
                break;
            default:
                throw new RuntimeException("不支持的OSS类型");
        }

        return storageService;
    }

}