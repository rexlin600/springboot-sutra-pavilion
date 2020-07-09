package xyz.rexlin600.easy.excel.util;

import java.io.File;
import java.io.InputStream;

/**
 * Excel 文件工具类
 *
 * @author: hekunlin
 * @since: 2020/3/9
 */
public class ExcelFilePathUtil {

    /**
     * 根据文件路径获取文件输入流
     *
     * @param fileName
     * @return
     */
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    /**
     * 获取本地 Excel 文件路径
     *
     * @return
     */
    public static String getPath() {
        return ExcelFilePathUtil.class.getResource("/alibaba/").getPath();
    }

    /**
     * 创建新文件
     *
     * @param pathName
     * @return
     */
    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    /**
     * 读取文件为 File
     *
     * @param pathName
     * @return
     */
    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

}