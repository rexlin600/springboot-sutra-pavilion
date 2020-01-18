package xyz.rexlin600.service.impl;

import xyz.rexlin600.entity.UserRole;
import xyz.rexlin600.mapper.UserRoleMapper;
import xyz.rexlin600.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关联表 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
