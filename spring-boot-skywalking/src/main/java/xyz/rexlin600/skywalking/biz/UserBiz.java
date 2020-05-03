package xyz.rexlin600.skywalking.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.skywalking.mapper.UserMapper;
import xyz.rexlin600.skywalking.model.User;

/**
 * User biz 类
 *
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Service
public class UserBiz extends ServiceImpl<UserMapper, User> {

}