package xyz.rexlin600.helloworld.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.helloworld.entity.AliApp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 14:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListPropertiesTest {

    @Autowired
    private ListProperties listProperties;

    @Test
    public void contextLoads() {
        log.info("==>  read list properteis: [{}]", listProperties.toString());
        List<AliApp> aliAppList = listProperties.getAliAppList();
        List<AliApp> collect = aliAppList.stream().filter(m -> m.getAppId().equals("2021001124610028")).collect(Collectors.toList());
        if (collect.size() == 1) {
            AliApp aliApp = collect.get(0);
            System.out.println(aliApp.getAppId());
            System.out.println(aliApp.getPrivateKey());
            System.out.println(aliApp.getAliPublicKey());
            System.out.println(aliApp.getAesKey());
        }
    }

}