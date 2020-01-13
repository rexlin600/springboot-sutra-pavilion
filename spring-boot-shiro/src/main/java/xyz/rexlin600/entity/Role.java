package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * Role 实体类
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

}