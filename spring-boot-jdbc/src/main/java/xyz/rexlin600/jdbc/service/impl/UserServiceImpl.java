package xyz.rexlin600.elasticsearch.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import xyz.rexlin600.docker.entity.User;
import xyz.rexlin600.elasticsearch.service.UserService;

/**
 * UserService 实现类
 *
 * @author: rexlin600
 * @date: 2020-01-10 22:12:50
 */
@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据id查找用户
     *
     * @param id
     */
    @Override
    public User selectById(Long id) {
        String sql = "select id,name,age,email from user where id=?";

        User user = jdbcTemplate.query(sql, new Object[]{id}, (ResultSetExtractor<User>) resultSet -> {
            if (resultSet.next()) {
                return User.builder()
                        .id(Long.valueOf(resultSet.getString("id")))
                        .name(resultSet.getString("name"))
                        .age(Integer.valueOf(resultSet.getString("age")))
                        .email(resultSet.getString("email"))
                        .build();
            } else {
                return null;
            }
        });

        return user;
    }

    /**
     * 根据id删除一个用户
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        String sql = "delete from user where id = " + id;
        jdbcTemplate.execute(sql);
    }

    /**
     * 获取用户总数
     *
     * @return
     */
    @Override
    public Long getAllUsers() {
        String sql = "select count(id) from user";
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        return result;
    }
}