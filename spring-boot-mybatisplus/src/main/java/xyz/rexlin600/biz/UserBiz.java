package xyz.rexlin600.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mapper.UserMapper;
import xyz.rexlin600.model.User;

/**
 * User biz 类
 *
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Service
public class UserBiz extends ServiceImpl<UserMapper, User> {

}