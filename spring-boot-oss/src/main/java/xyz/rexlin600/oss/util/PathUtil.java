package xyz.rexlin600.oss.util;

import org.springframework.util.StringUtils;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.File;
import java.io.IOException;

/**
 * 路径工具类
 *
 * @author: hekunlin
 * @date: 2020/6/22
 */
public class PathUtil {

    /**
     * 处理文件路径
     *
     * @param path     文件路径
     * @param fileName 文件名称
     * @return 返回形如 /aaa/bbb/xxx.png 形式的路径
     */
    public static String resolvePath(String path, String fileName) {
        // 去除多余的 /
        path = path.replaceAll(OssConstant.SLASH_PLUS, OssConstant.SLASH);

        // 处理前后缀
        if (StringUtils.isEmpty(path) || path.equals(OssConstant.SLASH)) {
            path = fileName;
        } else {
            if (!path.startsWith(OssConstant.SLASH)) {  // 增加前缀
                path = OssConstant.SLASH + path;
            }
            if (path.endsWith(OssConstant.SLASH)) { // 拼接 fileName
                path = path.concat(fileName);
            } else {
                path = path.concat(OssConstant.SLASH).concat(fileName);
            }
        }

        return path;
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        // 创建文件
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }


}