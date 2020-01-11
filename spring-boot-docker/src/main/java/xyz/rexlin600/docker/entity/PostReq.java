package xyz.rexlin600.docker.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Post请求测试类
 *
 * @author: hekunlin
 */
@Data
public class PostReq implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}