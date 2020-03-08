package xyz.rexlin600.starter.dds.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * RsaUtils 工具类
 *
 * @author: hekunlin
 */
public class AesUtils {

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        // 随机生成密钥
        byte[] key = DataSourceConstants.CONTENT.getBytes();
        // 构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        // 加密
        return aes.encryptHex(str, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        // 随机生成密钥
        byte[] key = DataSourceConstants.CONTENT.getBytes();
        // 构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        // 解密
        return aes.decryptStr(str, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        String password = "123456";
        String encrypt = encrypt(password);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt));
    }

}