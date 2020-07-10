package xyz.rexlin600.eureka.discovery;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * File upload rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadRest {

	/**
	 * Upload string
	 *
	 * @param file file
	 * @return the string
	 */
	@SneakyThrows
	@PostMapping("/upload")
	public String upload(@RequestParam(value = "file") MultipartFile file) {
		String filename = file.getOriginalFilename();
		long fileSize = file.getSize();
		InputStream inputStream = file.getInputStream();

		log.info("==>  fileName is : {}", filename);
		log.info("==>  fileSize is : {}", fileSize);

		// 省略文件上传后续操作 ...

		if (inputStream != null) {
			inputStream.close();
		}

		return "SUCCESS";
	}

}