package xyz.rexlin600.jdbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * User 类
 *
 * @author: rexlin600
 * @since: 2020-01-10 22:10:41
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