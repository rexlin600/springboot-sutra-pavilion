package xyz.rexlin600.mail.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.jdbc.entity.TbFlyway;
import xyz.rexlin600.mail.service.FlywayService;

import java.util.Date;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlywayServiceImplTest {

    @Autowired
    private FlywayService flywayService;

    @Test
    public void save() {
        TbFlyway build = TbFlyway.builder()
                .id(Long.valueOf((int) (Math.random() * 10001)))
                .name(String.valueOf((Math.random() * 10001)))
                .length(174.99)
                .createTime(new Date())
                .build();
        flywayService.save(build);
    }

    @Test
    public void findOne() {
        TbFlyway flyway = flywayService.findOne(1L);
        log.info("==>  flyway is [{}]", flyway);
    }

    @Test
    public void findCount() {
        Long count = flywayService.findCount();
        log.info("==>  tb_flyway count is [{}]", count);
    }
}