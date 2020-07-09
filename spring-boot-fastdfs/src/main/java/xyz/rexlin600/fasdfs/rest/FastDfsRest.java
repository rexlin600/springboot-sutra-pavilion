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
 * FastDfs 文件上传下载接口
 *
 * @author: hekunlin
 * @since: 2020/5/8
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDfsRest {

    private final FastFileStorageClient fileStorageClient;
    private final FastDfsConfigBean fastDfsConfigBean;

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
     * fastDFS 文件上传
     *
     * @param file
     * @return
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
     * fastDFS 文件下载
     *
     * @param groupName 文件在 fastDFS 的组名称，如：group1
     * @param path      文件在 fastDFS 的相对路径（不包含 groupName），如：M00/00/27/CsXsq160-XOAKZmDAAH7g0hg3gc139.jpg
     * @param outPath   下载文件输出位置，如：.
     * @return
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