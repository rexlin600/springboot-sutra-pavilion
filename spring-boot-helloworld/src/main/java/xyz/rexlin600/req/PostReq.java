package xyz.rexlin600.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Post请求测试类
 *
 * @author: hekunlin
 * @date: 2020/1/2 13:57:29
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