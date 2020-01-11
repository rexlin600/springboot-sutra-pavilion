package xyz.rexlin600.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import xyz.rexlin600.jdbc.entity.TbFlyway;
import xyz.rexlin600.mail.service.FlywayService;

/**
 * FlywayServiceImpl 实现类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
@Service
public class FlywayServiceImpl implements FlywayService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FlywayServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TbFlyway tbFlyway) {
        String sql = "insert into tb_flyway(id, name, length, create_time) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, tbFlyway.getId(), tbFlyway.getName(), tbFlyway.getLength(), tbFlyway.getCreateTime());
    }

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

    @Override
    public Long findCount() {
        String sql = "select count(id) from tb_flyway";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count;
    }
}