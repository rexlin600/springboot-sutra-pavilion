package xyz.rexlin600.mybatisplus.codegen.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AesUtilsTest {

    public static final String pwd = "124wgrqr131gfewtr231";

    @Test
    public void encryptAndDecrypt() {
        String encrypt = AesUtils.encrypt(pwd);
        String decrypt = AesUtils.decrypt(encrypt);
        assertEquals(pwd, decrypt);
    }

}