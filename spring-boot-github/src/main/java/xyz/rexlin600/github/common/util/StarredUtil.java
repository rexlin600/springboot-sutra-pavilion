package xyz.rexlin600.github.common.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import xyz.rexlin600.github.entity.StarredResp;

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
    public static void main(String[] args) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson("{\n" +
                "    \"id\": 159152904,\n" +
                "    \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNTkxNTI5MDQ=\",\n" +
                "    \"name\": \"jeecg-boot\",\n" +
                "    \"full_name\": \"zhangdaiscott/jeecg-boot\",\n" +
                "    \"private\": false,\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"zhangdaiscott\",\n" +
                "      \"id\": 3162115,\n" +
                "      \"node_id\": \"MDQ6VXNlcjMxNjIxMTU=\",\n" +
                "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/3162115?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/zhangdaiscott\",\n" +
                "      \"html_url\": \"https://github.com/zhangdaiscott\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/zhangdaiscott/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/zhangdaiscott/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/zhangdaiscott/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/zhangdaiscott/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/zhangdaiscott/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/zhangdaiscott/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/zhangdaiscott/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/zhangdaiscott/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/zhangdaiscott/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"html_url\": \"https://github.com/zhangdaiscott/jeecg-boot\",\n" +
                "    \"description\": \"一款基于代码生成器的JAVA快速开发平台，开源界“小普元”超越传统商业企业级开发平台！采用前后端分离架构：SpringBoot 2.x，Ant Design&Vue，Mybatis-plus，Shiro，JWT。强大的代码生成器让前后端代码一键生成，无需写任何代码! 引领新的开发模式(OnlineCoding模式-> 代码生成器模式-> 手工MERGE智能开发)，帮助Java项目解决70%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省成本，同时又不失灵活性。JeecgBoot还独创在线开发模式（No代码）：在线表单配置（表单设计器）、移动配置能力、在线工作流配置（流程设计器）、在线报表配置、在线图表配置、插件能力（可插拔）等等\",\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/zhangdaiscott/jeecg-boot/deployments\",\n" +
                "    \"created_at\": \"2018-11-26T10:40:00Z\",\n" +
                "    \"updated_at\": \"2020-03-22T04:35:19Z\",\n" +
                "    \"pushed_at\": \"2020-03-12T10:48:13Z\",\n" +
                "    \"git_url\": \"git://github.com/zhangdaiscott/jeecg-boot.git\",\n" +
                "    \"ssh_url\": \"git@github.com:zhangdaiscott/jeecg-boot.git\",\n" +
                "    \"clone_url\": \"https://github.com/zhangdaiscott/jeecg-boot.git\",\n" +
                "    \"svn_url\": \"https://github.com/zhangdaiscott/jeecg-boot\",\n" +
                "    \"homepage\": \"http://www.jeecg.com\",\n" +
                "    \"size\": 31527,\n" +
                "    \"stargazers_count\": 10440,\n" +
                "    \"watchers_count\": 10440,\n" +
                "    \"language\": \"Java\",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_projects\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": true,\n" +
                "    \"forks_count\": 3921,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"archived\": false,\n" +
                "    \"disabled\": false,\n" +
                "    \"open_issues_count\": 21,\n" +
                "    \"license\": {\n" +
                "      \"key\": \"apache-2.0\",\n" +
                "      \"name\": \"Apache License 2.0\",\n" +
                "      \"spdx_id\": \"Apache-2.0\",\n" +
                "      \"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
                "      \"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
                "    },\n" +
                "    \"forks\": 3921,\n" +
                "    \"open_issues\": 21,\n" +
                "    \"watchers\": 10440,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  }", JsonObject.class);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);

        List<StarredResp> list = convert(jsonArray);
        log.info("convert result is : {}", list.get(0).toString());
    }

}