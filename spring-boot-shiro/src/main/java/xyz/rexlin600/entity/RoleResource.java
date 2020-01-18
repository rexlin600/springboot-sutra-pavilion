package xyz.rexlin600.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@TableName("role_resource")
public class RoleResource extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
        "roleId=" + roleId +
        ", resourceId=" + resourceId +
        "}";
    }
}
