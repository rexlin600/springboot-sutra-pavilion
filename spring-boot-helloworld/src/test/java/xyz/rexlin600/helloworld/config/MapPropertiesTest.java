package xyz.rexlin600.helloworld.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-10 14:20
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapPropertiesTest {

    @Autowired
    private MapProperties mapProperties;

    @Autowired
    private WechatProperties wechatProperties;

    @Test
    public void contextLoads() {
        log.info("==>  read map properteis: [{}]", mapProperties.toString());
        String appId = "wxd31b505cb863efb1";
        boolean hasAppId = wechatProperties.getMap().containsKey(appId);
        if (hasAppId) {
            log.info("==>  read map properteis: [{}]", wechatProperties.getMap().get(appId));
        }
    }


}