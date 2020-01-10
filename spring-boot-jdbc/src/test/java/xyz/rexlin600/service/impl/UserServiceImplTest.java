package xyz.rexlin600.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.entity.User;
import xyz.rexlin600.service.UserService;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectById() {
        User user1 = userService.selectById(100L);
        User user2 = userService.selectById(1000L);
        log.info("==>  select User1 is {} ", user1);
        log.info("==>  select User2 is {} ", user2);
    }

    @Test
    public void deleteById() {
        userService.deleteById(100L);
    }

    @Test
    public void getAllUsers() {
        Long count = userService.getAllUsers();
        log.info("==>  select User table count is=[{}])", count);
    }
}