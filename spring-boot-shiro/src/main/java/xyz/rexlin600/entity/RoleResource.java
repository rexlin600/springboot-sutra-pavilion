package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RoleResource 实体类
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResource implements Serializable {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;

}