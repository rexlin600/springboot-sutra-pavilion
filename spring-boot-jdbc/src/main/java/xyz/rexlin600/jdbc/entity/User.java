package xyz.rexlin600.docker.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * User 类
 *
 * @author: rexlin600
 * @date: 2020-01-10 22:10:41
 */
@ToString
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}