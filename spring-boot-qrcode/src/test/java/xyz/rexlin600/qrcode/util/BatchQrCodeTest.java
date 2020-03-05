package xyz.rexlin600.qrcode.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ZipUtil;
import org.junit.Test;
import xyz.rexlin600.qrcode.base.constants.QrCodeConstant;
import xyz.rexlin600.qrcode.base.entity.BatchQrCode;
import xyz.rexlin600.qrcode.base.entity.QrCode;

import java.awt.image.BufferedImage;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-03 12:59
 */
public class BatchQrCodeTest {

    private static final String LOCAL_FILE_PATH = "C:\\Users\\hekunlin\\Pictures\\";

    /**
     * 批量生成二维码
     */
    @Test
    public void batchLogoQrCode() throws Exception {
        List<QrCode> list = new ArrayList<>();
        list.add(new QrCode("top", "content-1", "top", "", ""));
        list.add(new QrCode("center", "content-2", "", "center", ""));
        list.add(new QrCode("bottom", "content-3", "", "", "bottom"));
        list.add(new QrCode("all", "content-4", "top", "center", "bottom"));
        list.add(new QrCode("top-bottom", "content-5", "top", "", "bottom"));
        list.add(new QrCode("top-center", "content-6", "top", "center", ""));
        list.add(new QrCode("enter-bottom", "content-6", "", "center", "bottom"));

        List<BatchQrCode> bufferedImageList = QrCodeGenUtil.batchTextQrCode(list);
        AtomicInteger atomicInteger = new AtomicInteger(1);

        // 批量生成，不能使用并行流、无法保证 atomicInteger 的原子更新
        long milli = Instant.now().toEpochMilli();
        String path = LOCAL_FILE_PATH + File.separator + milli;
        // 创建文件夹
        File tempDir = new File(path);
        if (!tempDir.exists()) {
            boolean mkdirs = tempDir.mkdirs();
            if (BooleanUtil.isFalse(mkdirs)) {
                System.out.println("error create path");
                return;
            }
        }
        // 写入临时目录
        bufferedImageList.stream().forEach(m -> {
            try {
                String file = path + File.separator + m.getQrCode().getName() + "." + QrCodeConstant.PNG;
                QrCodeGenUtil.write2File(m.getBufferedImage(), QrCodeConstant.PNG, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            atomicInteger.incrementAndGet();
        });

        // 批量压缩
        ZipUtil.zip(path, LOCAL_FILE_PATH + milli + ".zip");

        // 删除临时目录
        FileUtil.del(LOCAL_FILE_PATH + "\\" + milli);

    }

}