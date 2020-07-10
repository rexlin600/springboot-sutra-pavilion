package xyz.rexlin600.mybatisplus.codegen.util;

import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Properties;

/**
 * Os utils
 *
 * @author hekunlin
 */
public class OsUtils {

	/**
	 * LINUX
	 */
	private static final String LINUX = "linux";

	/**
	 * Is os linux boolean
	 *
	 * @return the boolean
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
	 * Gets desktop path *
	 *
	 * @return the desktop path
	 */
	public static String getDesktopPath() {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		return desktopPath;
	}

}