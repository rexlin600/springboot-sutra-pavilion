package xyz.rexlin600.transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.rexlin600.transaction.entity.User;
import xyz.rexlin600.transaction.repository.UserRepository;
import xyz.rexlin600.transaction.service.UserService;

import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        Optional<User> optional = userRepository.findById(1L);
        if (!optional.isPresent()) {
            User user = new User();
            user.setId(1L);
            user.setName("rexlin600");
            user.setAge(24);
            user.setEmail("rexlin600@gmail.com");

            userRepository.save(user);

            userRepository.deleteById(2L);
        }
    }

    @Test
    public void findById() {
        User user = userService.findById(1L);
        log.info("==>  find by id, user is = [{}]", user);
    }

    /**
     * 下面这个测试用例理论上是不会通过的，会抛出 DuplicateKeyException。但是我这里手动捕获了异常避免跑测试不通过
     */
    @Test
    public void addUser1() {
        User user = new User();
        user.setId(1L);
        user.setName("rexlin600");
        user.setAge(24);
        user.setEmail("rexlin600@gmail.com");
        try {
            userService.addUser(user);
        } catch (DuplicateKeyException exception) {
            log.error("==>  this test is failed, catched ex=[{}]", exception.getMessage());
        }
    }


    /**
     * 事务测试，回滚：因为发生了异常
     * <p>
     * 下面这个测试用例会通过，但是会提示：Rolled back transaction for test ...
     */
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    public void addUser2() {
        User user = new User();
        user.setId(9L);
        user.setName("rexlin999");
        user.setAge(99);
        user.setEmail("99@gmail.com");

        try {
            userService.addUser(user);
            // 笔者在这里遇到一个小问题，就是 jpa 的配置写为了 create，结果生成的数据库表为 MyIsam 的引擎！
            int r = 1 / 0;
        } catch (Exception e) {
            // catch ex, rollback
            log.error("==>  this test is failed, catched ex=[{}]", e.getMessage());
        }
    }

    /**
     * 这里异常不会回滚
     * <p>
     * 如果之前数据库存在 id=1 的数据，那么这里执行之后还会新增一条 id=2 的数据
     */
    @Test
    public void addUser3() {
        User user2 = new User();
        user2.setId(2L);
        user2.setName("rexlin700");
        user2.setAge(25);
        user2.setEmail("rexlin700@gmail.com");

        try {
            userService.addUser(user2); // success
            int i = 1 / 0;  // catch ex, but won't rollback
        } catch (Exception e) {
            log.error("==>  this test is failed, catched ex=[{}]", e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------------------
    // jpa method
    // -----------------------------------------------------------------------------------------------
    @Test
    public void findByName() {
        User user = userRepository.findByName("rexlin600");
        log.info("==>  jpa search, user is = [{}]", user);
    }

    @Test
    public void findByNameAndAge() {
        User user = userRepository.findByNameAndAge("rexlin600", 24);
        log.info("==>  jpa search, user is = [{}]", user);
    }

    @Test
    public void findUser() {
        User user = userRepository.findUser("rexlin600");
        log.info("==>  jpa search, user is = [{}]", user);
    }


}