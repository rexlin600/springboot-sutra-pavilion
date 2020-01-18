package xyz.rexlin600.service.impl;

import xyz.rexlin600.entity.Role;
import xyz.rexlin600.mapper.RoleMapper;
import xyz.rexlin600.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
