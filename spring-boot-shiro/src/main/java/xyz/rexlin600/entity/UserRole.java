package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * UserRole 关联表
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRole implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}