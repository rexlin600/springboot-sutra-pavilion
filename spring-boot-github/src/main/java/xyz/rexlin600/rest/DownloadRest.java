package xyz.rexlin600.rest;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.Download;
import org.eclipse.egit.github.core.DownloadResource;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.config.runner.GRunner;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Github下载API
 *
 * @author: rexlin600
 * @date: 2020-01-05 19:16:12
 */
@RestController
@RequestMapping(value = "/download")
public class DownloadRest {


    /**
     * 1. 【分页下载】
     *
     * @param repository
     * @param start
     * @param size
     * @return
     */
    @SneakyThrows
    @PostMapping("/page/downloads")
    public Response pageDownloads(@RequestBody IRepositoryIdProvider repository,
                                  @RequestParam(value = "start", required = false) Integer start,
                                  @RequestParam(value = "size", required = false) Integer size) {
        PageIterator<Download> pageIterator = null;
        List<Download> list = Collections.emptyList();

        // start and size is null
        if (ObjectUtils.isEmpty(start) && ObjectUtils.isEmpty(size)) {
            pageIterator = GRunner.downloadService.pageDownloads(repository);
        }

        // start is null
        if (ObjectUtils.isEmpty(start)) {
            pageIterator = GRunner.downloadService.pageDownloads(repository, size);
        }

        // start and size is not null
        if (!ObjectUtils.isEmpty(start) && !ObjectUtils.isEmpty(size)) {
            pageIterator = GRunner.downloadService.pageDownloads(repository, start, size);
        }

        // merge
        while (pageIterator.hasNext()) {
            Collection<Download> collection = pageIterator.next();
            list.addAll(collection);
        }

        return ResponseGenerator.success();
    }


    /**
     * 2. 【获取下载信息】
     *
     * @param repository
     * @param id
     * @return
     */
    @SneakyThrows
    @DeleteMapping("/getDownload")
    public Response getDownload(@RequestBody IRepositoryIdProvider repository, Integer id) {
        Download download = GRunner.downloadService.getDownload(repository, id);
        return ResponseGenerator.success(download);
    }


    /**
     * 3. 【上传资源】
     *
     * @param resource
     * @param content
     * @param size
     * @return
     */
    @SneakyThrows
    @DeleteMapping("/uploadResource")
    public Response uploadResource(@RequestBody DownloadResource resource,
                                   @RequestParam("content") InputStream content,
                                   @RequestParam("size") long size) {
        GRunner.downloadService.uploadResource(resource, content, size);
        return ResponseGenerator.success();
    }


    /**
     * 4. 【删除下载】
     *
     * @param repository
     * @param id
     * @return
     */
    @SneakyThrows
    @DeleteMapping("/deleteDownload")
    public Response deleteDownload(@RequestBody IRepositoryIdProvider repository,
                                   @RequestParam(value = "id") Integer id) {
        GRunner.downloadService.deleteDownload(repository, id);
        return ResponseGenerator.success();
    }


}