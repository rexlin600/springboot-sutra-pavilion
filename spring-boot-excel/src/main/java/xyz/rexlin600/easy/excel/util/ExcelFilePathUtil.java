package xyz.rexlin600.easy.excel.util;

import java.io.File;
import java.io.InputStream;

/**
 * Excel file path util
 *
 * @author hekunlin
 */
@SuppressWarnings("ALL")
public class ExcelFilePathUtil {

	/**
	 * Gets resources file input stream *
	 *
	 * @param fileName file name
	 * @return the resources file input stream
	 */
	public static InputStream getResourcesFileInputStream(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
	}

	/**
	 * Gets path *
	 *
	 * @return the path
	 */
	public static String getPath() {
		return ExcelFilePathUtil.class.getResource("/alibaba/").getPath();
	}

	/**
	 * Create new file file
	 *
	 * @param pathName path name
	 * @return the file
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
	 * Read file file
	 *
	 * @param pathName path name
	 * @return the file
	 */
	public static File readFile(String pathName) {
		return new File(getPath() + pathName);
	}

}