package xyz.rexlin600.github.common.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.CharsetUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import xyz.rexlin600.github.common.constant.GithubConstant;
import xyz.rexlin600.github.entity.StarredResp;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * Star 工具类
 *
 * @author: rexlin600
 * @date: 2020/3/22
 */
@Slf4j
public class StarredUtil {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("github4starred-pool-%d").build();
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            10,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 拼接获取Star列表URL
     *
     * @param page
     * @param user
     * @return
     */
    public static String getGithuStarsbUrl(Integer page, String user) {
        String starredUrl = new StringBuffer().append(GithubConstant.GITHUB_API_URL)
                .append(GithubConstant.GITHUB_USERS).append("/").append(user)
                .append(GithubConstant.GITHUB_STARRED)
                .append("?page=").append(page)
                .toString();
        return starredUrl;
    }

    /**
     * json 转 StarredResp
     *
     * @param jsonArray
     * @return
     */
    @SneakyThrows
    public static List<StarredResp> convert(JsonArray jsonArray) {
        List<StarredResp> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(jsonArray.size());

        for (JsonElement element : jsonArray) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 下列不会为空
                    JsonObject jsonObject = element.getAsJsonObject();
                    String fullName = jsonObject.get("full_name").getAsString();
                    String name = jsonObject.get("name").getAsString();
                    String author = jsonObject.get("owner").getAsJsonObject().get("login").getAsString();
                    String htmlUrl = jsonObject.get("html_url").getAsString();
                    String cloneUrl = jsonObject.get("clone_url").getAsString();
                    LocalDateTime createAt = LocalDateTime.parse(jsonObject.get("created_at").getAsString(), DateTimeFormatter.ISO_DATE_TIME);
                    LocalDateTime updateAt = LocalDateTime.parse(jsonObject.get("updated_at").getAsString(), DateTimeFormatter.ISO_DATE_TIME);
                    String defaultBranch = jsonObject.get("default_branch").getAsString();
                    long size = jsonObject.get("size").getAsLong();
                    long stargazersCount = jsonObject.get("stargazers_count").getAsLong();
                    long watchersCount = jsonObject.get("watchers_count").getAsLong();
                    long forksCount = jsonObject.get("forks_count").getAsLong();

                    // 构建对象
                    StarredResp starredResp = StarredResp.builder()
                            .fullName(fullName)
                            .name(name)
                            .author(author)
                            .htmlurl(htmlUrl)
                            .createAt(createAt)
                            .updateAt(updateAt)
                            .cloneUrl(cloneUrl)
                            .size(size)
                            .stargazersCount(stargazersCount)
                            .watchersCount(watchersCount)
                            .forksCount(forksCount)
                            .defaultBranch(defaultBranch)
                            .build();

                    // 可能为空
                    if (Optional.of(jsonObject.get("description")).isPresent()) {
                        starredResp.setDescription(jsonObject.get("description").toString().trim());
                    }
                    if (Optional.of(jsonObject.get("homepage")).isPresent()) {
                        starredResp.setHomePage(jsonObject.get("homepage").toString().trim());
                    }
                    if (Optional.of(jsonObject.get("language")).isPresent()) {
                        starredResp.setLanguage(jsonObject.get("language").toString().trim());
                    }
                    if (jsonObject.get("license") instanceof JsonObject && Optional.of(jsonObject.get("license")).isPresent()) {
                        starredResp.setLicense(jsonObject.get("license").getAsJsonObject().get("spdx_id").getAsString().trim());
                    }

                    list.add(starredResp);
                    countDownLatch.countDown();
                }
            });
        }

        // 等待所有处理完再返回
        countDownLatch.await();

        return list;
    }

    /**
     * 测试
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        // 读取 resource 下的文件，参考：https://www.jianshu.com/p/7d7e5e4e8ae3
        InputStream inputStream = ResourceUtil.getStream("github.json");
        String json = IoUtil.read(inputStream, CharsetUtil.UTF_8);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);

        List<StarredResp> list = convert(jsonArray);
        log.info("convert result is : {}", list.get(0).toString());
    }

}