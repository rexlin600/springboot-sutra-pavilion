package xyz.rexlin600.oss.util;

import org.springframework.util.StringUtils;
import xyz.rexlin600.oss.common.OssConstant;

import java.io.File;
import java.io.IOException;

/**
 * Path util
 *
 * @author hekunlin
 */
public class PathUtil {

	/**
	 * SLASH
	 */
	private final static String SLASH = "/";

	/**
	 * Resolve path string
	 *
	 * @param path     path
	 * @param fileName file name
	 * @return the string
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
	 * Create file file
	 *
	 * @param path path
	 * @return the file
	 * @throws IOException io exception
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