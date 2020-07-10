package xyz.rexlin600.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import xyz.rexlin600.transaction.entity.User;
import xyz.rexlin600.transaction.service.UserService;

/**
 * User service
 *
 * @author hekunlin
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * Jdbc template
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find by id user
	 *
	 * @param id id
	 * @return the user
	 */
	@Override
	public User findById(Long id) {
		String sql = "select * from user where id = ?";
		User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
		return user;
	}

	/**
	 * Add user *
	 *
	 * @param user user
	 */
	@Override
	public void addUser(User user) {
		String sql = "insert into user(id,name,age,email) values(?,?,?,?)";
		jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAge(), user.getEmail());
	}

}