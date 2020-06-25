package xyz.rexlin600.oss.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * OSS 存储对象
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
public interface StorageService {

    /**
     * 文件上传
     *
     * @param data     文件字节数组
     * @param fileName 文件名称
     * @param path     文件路径
     * @return 返回http地址
     * @throws IOException
     */
    String upload(byte[] data, String fileName, String path) throws IOException;

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param fileName    文件名称
     * @param path        文件路径
     * @return 返回http地址
     * @throws IOException
     */
    String upload(InputStream inputStream, String fileName, String path) throws IOException;

    /**
     * 阿里云下载文件
     *
     * @param key 文件key
     * @return
     * @throws IOException
     */
    InputStream download(String key) throws IOException;

    /**
     * 腾讯云下载文件
     *
     * @param key  文件key
     * @param path 本地路径
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    void download(String key, String path) throws InterruptedException, IOException;

    /**
     * 删除指定 key
     *
     * @param key 文件key
     * @throws IOException
     */
    void delete(String key) throws IOException;

}