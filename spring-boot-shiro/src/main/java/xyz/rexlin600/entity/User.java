package xyz.rexlin600.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户是否激活 0-未激活 1-已激活
     */
    private Integer active;

    /**
     * 用户角色列表
     */
    private Set<String> roleList;

    /**
     * 用户资源列表
     */
    private Set<String> resourceList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", active=" + active +
                '}';
    }
}
