package xyz.rexlin600.fasdfs.rest;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadFileWriter;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.rexlin600.fasdfs.common.apiparam.Response;
import xyz.rexlin600.fasdfs.common.apiparam.ResponseGenerator;
import xyz.rexlin600.fasdfs.common.resp.FastDfsUploadResp;
import xyz.rexlin600.fasdfs.configbean.FastDfsConfigBean;

import java.util.function.Function;

/**
 * Fast dfs rest
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDfsRest {

	/**
	 * File storage client
	 */
	private final FastFileStorageClient fileStorageClient;
	/**
	 * Fast dfs config bean
	 */
	private final FastDfsConfigBean fastDfsConfigBean;

	/**
	 * Fast dfs rest
	 *
	 * @param fileStorageClient file storage client
	 * @param fastDfsConfigBean fast dfs config bean
	 */
	@Autowired
	public FastDfsRest(FastFileStorageClient fileStorageClient,
					   FastDfsConfigBean fastDfsConfigBean) {
		this.fileStorageClient = fileStorageClient;
		this.fastDfsConfigBean = fastDfsConfigBean;
	}

	// -----------------------------------------------------------------------------------------------
	// API
	// -----------------------------------------------------------------------------------------------

	/**
	 * Upload response
	 *
	 * @param file file
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response<FastDfsUploadResp> upload(@RequestPart(value = "file") MultipartFile file) {
		// 上传
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		FastFile fastFile = new FastFile.Builder().withFile(file.getInputStream(), file.getSize(), extension).build();
		StorePath storePath = fileStorageClient.uploadFile(fastFile);

		// fastDFS 相对路径
		String relativePath = storePath.getFullPath();
		// fastDFS绝对路径
		String fullPath = fastDfsConfigBean.getHttpUrl() + relativePath;

		// 返回类
		FastDfsUploadResp fastDfsUploadResp = new FastDfsUploadResp();
		fastDfsUploadResp.setRelativePath(relativePath);
		fastDfsUploadResp.setFullPath(fullPath);

		return ResponseGenerator.success(fastDfsUploadResp);
	}

	/**
	 * Downlaoad response
	 *
	 * @param groupName group name
	 * @param path      path
	 * @param outPath   out path
	 * @return the response
	 */
	@PostMapping("/downlaoad")
	public Response downlaoad(@RequestParam(value = "groupName") String groupName,
							  @RequestParam(value = "path") String path,
							  @RequestParam(value = "outPath") String outPath) {
		// 参数地址转换
		Function<String, String> outPathCovert = s -> s.endsWith("/") ? s : (s + "/");
		outPath = outPathCovert.apply(outPath) + path.substring(path.lastIndexOf("/") + 1);


		// 下载文件
		String downloadFile = fileStorageClient.downloadFile(groupName, path, new DownloadFileWriter(outPath));

		return ResponseGenerator.success(downloadFile);
	}

}