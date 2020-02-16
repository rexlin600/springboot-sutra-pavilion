package xyz.rexlin600.elasticsearch.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.rexlin600.elasticsearch.model.User;
import xyz.rexlin600.elasticsearch.service.UserService;

/**
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Slf4j
@Component
public class EsRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("==>  ES demo is start running ... ");

        User user = User.builder()
                .id(System.currentTimeMillis())
                .name("rexlin600")
                .remark("his name is rexlin600!")
                .age(24)
                .build();

        log.info("==>  ready to save user:{}", user);

        userService.create(user);
        log.info("==>  user has bean saved:{}", user);

        log.info("==>  find all users:{}", userService.findAll());
    }

}