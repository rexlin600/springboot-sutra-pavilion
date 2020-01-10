package xyz.rexlin600.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 13:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimplePropertiesTest {

    @Autowired
    private SimpleProperties simpleProperties;

    @Test
    public void contextLoads() {
        log.info("==>  read simple properteis: [{}]", simpleProperties.toString());
    }
}