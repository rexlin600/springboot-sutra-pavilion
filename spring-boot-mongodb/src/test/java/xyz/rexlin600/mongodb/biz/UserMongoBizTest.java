package xyz.rexlin600.mongodb.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import xyz.rexlin600.mongodb.entity.User;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMongoBizTest {

    @Autowired
    private UserMongoBiz userMongoBiz;

    @Before
    public void setUp() throws Exception {
        User build = User.builder()
                .id(371L)
                .name("25")
                .age(20)
                .build();
        userMongoBiz.saveUser(build);
    }

    @Test
    public void saveUser() {
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .id(Long.valueOf((int) (Math.random() * 10001)))
                    .name(String.valueOf((int) (Math.random() * 101)))
                    .age((int) (Math.random() * 101))
                    .build();
            userMongoBiz.saveUser(user);
        }
    }

    @Test
    public void getById() {
        User user = userMongoBiz.getById(371L);
        log.info("==>  fin  d by id, users is [{}]", user);
    }

    @Test
    public void update() {
        User user = userMongoBiz.getById(371L);
        if (!ObjectUtils.isEmpty(user)) {
            log.info("==>  before update, find by id, users is [{}]", user);
            user.setName("update" + user.getName());
            userMongoBiz.update(user);
        }
        user = userMongoBiz.getById(371L);
        log.info("==>  find by id, users is [{}]", user);
    }

    @Test
    public void findByName() {
        List<User> users = userMongoBiz.findByName("25");
        log.info("==>  find by name, users is [{}]", users);
    }

    @Test
    public void deleteUserById() {
        userMongoBiz.deleteUserById(371L);
    }

    @Test
    public void getUserList() {
        List<User> userList = userMongoBiz.getUserList();
        log.info("==>  mongodb list is [{}]", userList);
    }

}