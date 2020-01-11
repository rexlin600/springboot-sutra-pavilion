package xyz.rexlin600.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.rexlin600.entity.User;

/**
 * UserService 类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
public interface UserService {

    User findById(Long id);

    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    void addUser(User user);

}