package xyz.rexlin600.skywalking.model;

import lombok.Data;

/**
 * @author rexlin600
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}