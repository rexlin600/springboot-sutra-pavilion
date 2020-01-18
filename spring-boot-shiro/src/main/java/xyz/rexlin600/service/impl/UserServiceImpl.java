package xyz.rexlin600.service.impl;

import xyz.rexlin600.entity.User;
import xyz.rexlin600.mapper.UserMapper;
import xyz.rexlin600.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
