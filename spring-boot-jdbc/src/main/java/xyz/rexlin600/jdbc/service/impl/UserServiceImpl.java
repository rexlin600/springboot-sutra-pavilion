package xyz.rexlin600.jdbc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import xyz.rexlin600.jdbc.entity.User;
import xyz.rexlin600.jdbc.service.UserService;

/**
 * User service
 *
 * @author hekunlin
 */
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {

	/**
	 * Jdbc template
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * User service
	 *
	 * @param jdbcTemplate jdbc template
	 */
	@Autowired
	public UserServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Select by id user
	 *
	 * @param id id
	 * @return the user
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
	 * Delete by id *
	 *
	 * @param id id
	 */
	@Override
	public void deleteById(Long id) {
		String sql = "delete from user where id = " + id;
		jdbcTemplate.execute(sql);
	}

	/**
	 * Gets all users *
	 *
	 * @return the all users
	 */
	@Override
	public Long getAllUsers() {
		String sql = "select count(id) from user";
		Long result = jdbcTemplate.queryForObject(sql, Long.class);
		return result;
	}
}