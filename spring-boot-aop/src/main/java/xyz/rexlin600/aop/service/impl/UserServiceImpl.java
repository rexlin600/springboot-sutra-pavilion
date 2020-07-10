package xyz.rexlin600.aop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.aop.entity.User;
import xyz.rexlin600.aop.mapper.UserMapper;
import xyz.rexlin600.aop.service.UserService;

/**
 * User service
 *
 * @author hekunlin
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
