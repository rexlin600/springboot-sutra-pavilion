package xyz.rexlin600.ocr.ocr.core;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;

/**
 * Ocr 文件工具类
 *
 * @author hekunlin
 * @since 2020 /8/13
 */
public class OcrFileUtil {

	// -----------------------------------------------------------------------------------------------
	// UTIL
	// -----------------------------------------------------------------------------------------------

	/**
	 * Input stream 2 bytes byte []
	 *
	 * @param filePath filePath
	 * @return the byte []
	 * @throws IOException io exception
	 */
	public static byte[] readFileByBytes(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		}

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(((int) file.length()))) {
			BufferedInputStream in = null;
			in = new BufferedInputStream(new FileInputStream(file));
			int bufSize = 1024;
			byte[] buffer = new byte[bufSize];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, bufSize))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		}
	}

	/**
	 * Read file by bytes byte []
	 *
	 * @param file file
	 * @return the byte []
	 * @throws IOException io exception
	 */
	public static byte[] readFileByBytes(MultipartFile file) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(((int) file.getSize()))) {
			InputStream in = null;
			in = file.getInputStream();
			int bufSize = 1024;
			byte[] buffer = new byte[bufSize];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, bufSize))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		}
	}

	/**
	 * Build multipart file multipart file
	 *
	 * @param file file
	 * @return the multipart file
	 */
	public static MultipartFile buildMultipartFile(File file) {
		InputStream input = null;
		OutputStream os = null;
		MultipartFile multipartFile = null;
		try {
			FileItem fileItem = new DiskFileItem("mainFile",
					Files.probeContentType(file.toPath()),
					false,
					file.getName(),
					(int) file.length(),
					file.getParentFile());
			input = new FileInputStream(file);
			os = fileItem.getOutputStream();
			IOUtils.copy(input, os);
			multipartFile = new CommonsMultipartFile(fileItem);
			input.close();
			os.close();
		} catch (IOException ex) {
			throw new RuntimeException();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return multipartFile;
	}


}