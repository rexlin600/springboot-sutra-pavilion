package xyz.rexlin600.openfeign.svc.feign;

import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传Feign
 *
 * @author: hekunlin
 * @since: 2020/5/7
 */
@FeignClient(name = "fileUpload", url = "http://localhost:10032/file", configuration = {FeignAutoConfiguration.class})
public interface FileUploadFeign {

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart(value = "file") MultipartFile file);

}