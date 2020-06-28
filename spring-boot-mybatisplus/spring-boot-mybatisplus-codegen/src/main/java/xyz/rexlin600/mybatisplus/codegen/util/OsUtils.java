package xyz.rexlin600.mybatisplus.codegen.util;

import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Properties;

/**
 * OsUtils 工具类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
public class OsUtils {

    private static final String LINUX = "linux";

    /**
     * 判断 OS 是否为 Linux
     *
     * @return
     */
    public static boolean isOsLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (StringUtils.isEmpty(os)) {
            return false;
        }
        if (os.toLowerCase().indexOf(LINUX) <= -1) {
            return false;
        }

        return true;
    }

    /**
     * 获取操作系统桌面路径
     *
     * @return
     */
    public static String getDesktopPath() {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        return desktopPath;
    }

}