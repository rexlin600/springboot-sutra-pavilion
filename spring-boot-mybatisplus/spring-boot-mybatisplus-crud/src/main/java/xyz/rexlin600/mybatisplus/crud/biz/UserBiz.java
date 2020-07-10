package xyz.rexlin600.mybatisplus.crud.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mybatisplus.crud.mapper.UserMapper;
import xyz.rexlin600.mybatisplus.crud.model.User;

/**
 * User biz
 *
 * @author hekunlin
 */
@Service
public class UserBiz extends ServiceImpl<UserMapper, User> {

}