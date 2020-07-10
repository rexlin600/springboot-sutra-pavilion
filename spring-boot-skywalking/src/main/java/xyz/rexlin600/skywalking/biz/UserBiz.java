package xyz.rexlin600.skywalking.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.skywalking.mapper.UserMapper;
import xyz.rexlin600.skywalking.model.User;

/**
 * User biz
 *
 * @author hekunlin
 */
@Service
public class UserBiz extends ServiceImpl<UserMapper, User> {

}