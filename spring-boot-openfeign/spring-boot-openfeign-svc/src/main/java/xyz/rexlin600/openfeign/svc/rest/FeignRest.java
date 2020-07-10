package xyz.rexlin600.openfeign.svc.rest;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.rexlin600.openfeign.svc.feign.FileUploadFeign;
import xyz.rexlin600.openfeign.svc.feign.RemoteUriFeign;
import xyz.rexlin600.openfeign.svc.feign.resp.HistoryTodayResponse;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * Feign rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/open/feign")
public class FeignRest {

	/**
	 * Remote uri feign
	 */
	@Resource
	private RemoteUriFeign remoteUriFeign;

	/**
	 * File upload feign
	 */
	@Resource
	private FileUploadFeign fileUploadFeign;

	/**
	 * History toady history today response
	 *
	 * @param key   key
	 * @param v     v
	 * @param month month
	 * @param day   day
	 * @return the history today response
	 */
	@GetMapping("/historyToady")
	public HistoryTodayResponse historyToady(@RequestParam(value = "key") String key,
											 @RequestParam(value = "v") String v,
											 @RequestParam(value = "month") Integer month,
											 @RequestParam(value = "day") Integer day) {
		String s = remoteUriFeign.historyToady(key, v, month, day);
		Gson gson = new Gson();
		HistoryTodayResponse historyTodayResponse = gson.fromJson(s, HistoryTodayResponse.class);
		return historyTodayResponse;
	}

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

	/**
	 * Upload string
	 *
	 * @param files files
	 * @return the string
	 */
	@SneakyThrows
	@PostMapping("/batch/upload")
	public String upload(@RequestParam(value = "files") MultipartFile[] files) {
		for (MultipartFile file : files) {
			this.upload(file);
		}

		// 省略文件上传后续操作 ...

		return "SUCCESS";
	}

	/**
	 * Feign upload string
	 *
	 * @param file file
	 * @return the string
	 */
	@SneakyThrows
	@PostMapping("/feign/upload")
	public String feignUpload(@RequestParam(value = "file") MultipartFile file) {
		return fileUploadFeign.upload(file);
	}

}