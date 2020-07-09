package xyz.rexlin600.oss.storage;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.aliyun.oss.internal.OSSConstants;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import xyz.rexlin600.oss.common.OssConstant;
import xyz.rexlin600.oss.config.TxOssConfig;
import xyz.rexlin600.oss.util.PathUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯云存储服务实现类
 * <p>
 * default class：通过 OssFactory 暴露
 *
 * @author: hekunlin
 * @since: 2020/6/21
 */
@SuppressWarnings("DuplicatedCode")
@ConditionalOnBean(TxOssConfig.class)
@Service
class TxStorageServiceImpl implements StorageService {

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("oss-pool-%d").build();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            4,
            8,
            15,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    private TransferManager transferManager;
    private COSClient client;

    /**
     * 腾讯云配置
     */
    private final TxOssConfig config;

    public TxStorageServiceImpl(TxOssConfig config) {
        this.config = config;
        //初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        COSCredentials cred = new BasicCOSCredentials(config.getSecretId(), config.getSecretKey());
        Region region = new Region(config.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        client = new COSClient(cred, clientConfig);
        transferManager = new TransferManager(client, threadPoolExecutor);
    }

    @Override
    public String upload(byte[] data, String fileName, String path) throws IOException {
        return this.upload(new ByteArrayInputStream(data), fileName, path);
    }

    @Override
    public String upload(InputStream inputStream, String fileName, String path) throws IOException {
        path = PathUtil.resolvePath(path, fileName);

        //上传到腾讯云
        ObjectMetadata metadata = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(config.getBucketName(), path, inputStream, metadata);

        try {
            Upload upload = transferManager.upload(putObjectRequest);
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            throw new IOException("上传文件异常");
        } finally {
            transferManager.shutdownNow();
        }

        return config.getDomain().concat(OssConstant.SLASH).concat(path);
    }

    @Override
    public InputStream download(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(config.getBucketName(), key);
        COSObject cosObject = client.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInputStream = cosObject.getObjectContent();
        return cosObjectInputStream;
    }

    @Override
    public void download(String key, String path) throws InterruptedException, IOException {
        try {
            // 创建文件
            PathUtil.createFile(path);

            GetObjectRequest getObjectRequest = new GetObjectRequest(config.getBucketName(), key);
            getObjectRequest.setTrafficLimit(OssConstant.BANDWIDTH_10MB * OSSConstants.KB * OSSConstants.KB);
            // 下载文件
            Download download = transferManager.download(getObjectRequest, new File(path));
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            download.waitForCompletion();
        } finally {
            transferManager.shutdownNow();
        }
    }

    @Override
    public void delete(String key) {
        client.deleteObject(config.getBucketName(), key);
    }

}
