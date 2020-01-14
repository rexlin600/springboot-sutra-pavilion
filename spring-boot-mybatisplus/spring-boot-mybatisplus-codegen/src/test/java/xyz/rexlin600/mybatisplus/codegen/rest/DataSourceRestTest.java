package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;
import xyz.rexlin600.mybatisplus.codegen.service.DataSourceService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-14 18:58
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceRestTest {

    @Autowired
    private DataSourceService dataSourceService;

    @Test
    public void saveDataSource() {
        DataSource source = DataSource.builder()
                .id(999L)
                .host("localhost")
                .port("3306")
                .dbName("springboot-mybatisplus")
                .username("root")
                .password("123456")
                .createTime(new Date())
                .build();
        boolean save = dataSourceService.save(source);
        log.info("==>  save dataSource result: {}", save);
    }

    @Test
    public void deleteDataSource() {
        boolean b = dataSourceService.removeById(999L);
        log.info("==>  remove dataSource result: {}", b);
    }

    @Test
    public void updateDataSource() {
        DataSource source = DataSource.builder()
                .id(999L)
                .host("localhost")
                .port("3306")
                .dbName("springboot-mybatisplus")
                .username("root")
                .password("123456")
                .createTime(new Date())
                .build();
        boolean b = dataSourceService.updateById(source);
        log.info("==>  update dataSource result: {}", b);
    }

    @Test
    public void pageDataSource() {
        Page<DataSource> page = dataSourceService.page(new Page<>(1, 10));
        List<DataSource> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            records.stream().forEach(m -> {
                log.info(m.toString());
            });
        }
    }

    @Test
    public void getDataSource() {
        DataSource dataSource = dataSourceService.getOne(new LambdaQueryWrapper<DataSource>().eq(true, DataSource::getId, 1L));
        log.info("==>  get datasource {}", dataSource.toString());
    }
}