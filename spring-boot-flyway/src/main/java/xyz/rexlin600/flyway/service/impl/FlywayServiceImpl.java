package xyz.rexlin600.flyway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import xyz.rexlin600.flyway.entity.TbFlyway;
import xyz.rexlin600.flyway.service.FlywayService;

/**
 * Flyway service
 *
 * @author hekunlin
 */
@Service
public class FlywayServiceImpl implements FlywayService {

	/**
	 * Jdbc template
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * Flyway service
	 *
	 * @param jdbcTemplate jdbc template
	 */
	@Autowired
	public FlywayServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Save *
	 *
	 * @param tbFlyway tb flyway
	 */
	@Override
	public void save(TbFlyway tbFlyway) {
		String sql = "insert into tb_flyway(id, name, length, create_time) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, tbFlyway.getId(), tbFlyway.getName(), tbFlyway.getLength(), tbFlyway.getCreateTime());
	}

	/**
	 * Find one tb flyway
	 *
	 * @param id id
	 * @return the tb flyway
	 */
	@Override
	public TbFlyway findOne(Long id) {
		String sql = "select * from tb_flyway where id = ?";
		TbFlyway flyway = jdbcTemplate.query(sql, new Object[]{id}, (ResultSetExtractor<TbFlyway>) resultSet -> {
			if (resultSet.next()) {
				return TbFlyway.builder()
						.id(Long.valueOf(resultSet.getString("id")))
						.name(resultSet.getString("name"))
						.length(resultSet.getDouble("length"))
						.createTime(resultSet.getDate("create_time"))
						.build();
			} else {
				return null;
			}
		});
		return flyway;
	}

	/**
	 * Find count long
	 *
	 * @return the long
	 */
	@Override
	public Long findCount() {
		String sql = "select count(id) from tb_flyway";
		Long count = jdbcTemplate.queryForObject(sql, Long.class);
		return count;
	}
}