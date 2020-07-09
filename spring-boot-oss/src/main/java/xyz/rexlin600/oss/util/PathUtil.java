package xyz.rexlin600.oss.util;

import org.springframework.util.StringUtils;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.File;
import java.io.IOException;

/**
 * 路径工具类
 *
 * @author: hekunlin
 * @since: 2020/6/22
 */
public class PathUtil {

    private final static String SLASH = "/";

    /**
     * 处理文件路径
     *
     * @param path     文件路径
     * @param fileName 文件名称
     * @return 返回形如 aaa/bbb/xxx.png 形式的路径
     */
    public static String resolvePath(String path, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            throw new NullPointerException("文件名称为空");
        }

        // 处理 path
        if (!StringUtils.isEmpty(path)) {
            // 去除多余的 /
            path = path.replaceAll(OssConstant.SLASH_PLUS, OssConstant.SLASH);

            // 去前缀
            if (path.startsWith(SLASH)) {
                path = path.substring(1);
            }
            // 去后缀
            if (path.endsWith(OssConstant.SLASH)) {
                path = path.substring(0, path.length() - 1);
            }

            // 拼接 fileName
            fileName = fileName.replaceAll(OssConstant.SLASH_PLUS, OssConstant.SLASH);
            if (fileName.startsWith(SLASH)) {
                path = path.concat(fileName);
            } else {
                path = path.concat(SLASH).concat(fileName);
            }
        } else {
            // 拼接 fileName
            fileName = fileName.replaceAll(OssConstant.SLASH_PLUS, OssConstant.SLASH);
            if (fileName.startsWith(SLASH)) {
                path = fileName.substring(1);
            } else {
                path = fileName;
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