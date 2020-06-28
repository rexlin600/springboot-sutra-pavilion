package xyz.rexlin600.github.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Starred 返回类
 *
 * @author: rexlin600
 * @date: 2020/3/22
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StarredResp implements Serializable {

    /**
     * 项目全名
     */
    private String fullName;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * HTML 页面
     */
    private String htmlurl;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    /**
     * 克隆地址
     */
    private String cloneUrl;

    /**
     * 作者主页
     */
    private String homePage;

    /**
     * 项目大小
     */
    private Long size;

    /**
     * Star 总数
     */
    private Long stargazersCount;

    /**
     * 关注总数
     */
    private Long watchersCount;

    /**
     * fork总数
     */
    private Long forksCount;

    /**
     * 项目主要语言
     */
    private String language;

    /**
     * 项目许可证
     */
    private String license;

    /**
     * 默认分支
     */
    private String defaultBranch;

}