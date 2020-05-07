package xyz.rexlin600.eureka.discovery;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件上传
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@RestController
@RequestMapping("/file")
public class FileUploadRest {

    /**
     * Feign 文件上传
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        long fileSize = file.getSize();
        InputStream inputStream = file.getInputStream();

        // 省略文件上传后续操作 ...

        return "SUCCESS";
    }

}