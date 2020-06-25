package xyz.rexlin600.oss.rest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.rexlin600.oss.storage.OssFactory;
import xyz.rexlin600.oss.storage.StorageService;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * OSS Rest接口
 *
 * @author: hekunlin
 * @date: 2020/6/21
 */
@Slf4j
@RestController
@RequestMapping("/oss")
public class OssRest {

    private final OssFactory ossFactory;

    @Autowired
    public OssRest(OssFactory ossFactory) {
        this.ossFactory = ossFactory;
    }

    /**
     * 文件上传
     *
     * @param file    文件
     * @param ossType OSS类型
     */
    @SneakyThrows
    @PostMapping("/upload")
    public void upload(@RequestPart(value = "file") MultipartFile file,
                       @Range(min = 1, max = 3, message = "参数错误：OSS类型错误") @RequestParam(value = "ossType") Integer ossType,
                       @RequestParam(value = "path", required = false) String path) {
        StorageService storageService = ossFactory.build(ossType);

        String res = storageService.upload(file.getInputStream(), file.getOriginalFilename(), path);

        log.info("==>  文件上传结果：{}", res);
    }

    /**
     * 下载文件
     *
     * @param key      OSS对应bucket中的key
     * @param ossType  OSS类型
     * @param filePath 下载到本地的文件路径（含文件名！）
     */
    @SneakyThrows
    @GetMapping("/download")
    public void download(@RequestParam("key") String key,
                         @Range(min = 1, max = 3, message = "参数错误：OSS类型错误") @RequestParam(value = "ossType") Integer ossType,
                         @NotBlank(message = "参数错误：下载路径不可为空") @RequestParam("filePath") String filePath) {
        StorageService storageService = ossFactory.build(ossType);

        try {
            // 根据不同的 OSS 类型调用不同的下载
            storageService.download(key, filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("IO异常，下载文件失败");
        }
    }

    /**
     * 删除文件
     *
     * @param key     OSS对应bucket中的key
     * @param ossType OSS类型
     */
    @SneakyThrows
    @PostMapping("/delete")
    public void delete(@RequestParam("key") String key,
                       @Range(min = 1, max = 3, message = "参数错误：OSS类型错误") @RequestParam(value = "ossType") Integer ossType) {
        StorageService storageService = ossFactory.build(ossType);

        storageService.delete(key);
    }

}