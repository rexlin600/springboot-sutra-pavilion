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
 * 测试 rest
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@Slf4j
@RestController
@RequestMapping("/open/feign")
public class FeignRest {

    @Resource
    private RemoteUriFeign remoteUriFeign;

    @Resource
    private FileUploadFeign fileUploadFeign;

    /**
     * 查看某月某日的事件列表
     *
     * @param key
     * @param v
     * @param month
     * @param day
     * @return
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
     * SpringBoot 单个文件上传
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

        log.info("==>  fileName is : {}", filename);
        log.info("==>  fileSize is : {}", fileSize);

        // 省略文件上传后续操作 ...

        if (inputStream != null) {
            inputStream.close();
        }

        return "SUCCESS";
    }

    /**
     * SpringBoot 批量文件上传
     *
     * @param files
     * @return
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
     * Feign 文件上传
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/feign/upload")
    public String feignUpload(@RequestParam(value = "file") MultipartFile file) {
        return fileUploadFeign.upload(file);
    }

}