package xyz.rexlin600.elasticsearch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.rexlin600.docker.entity.User;
import xyz.rexlin600.elasticsearch.service.UserService;

/**
 * UserServiceImpl 实现类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(Long id) {
        String sql = "select * from user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user(id,name,age,email) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAge(), user.getEmail());
    }

}