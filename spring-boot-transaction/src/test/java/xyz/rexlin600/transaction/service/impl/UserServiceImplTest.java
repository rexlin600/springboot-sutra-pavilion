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
			log.info("==>  r is [{}]", r);
		} catch (Exception e) {
			// catch ex, rollback
			log.error("==>  this test is failed, catched ex=[{}]", e.getMessage());
		}
	}

	@Test
	public void addUser3() {
		User user2 = new User();
		user2.setId(2L);
		user2.setName("rexlin700");
		user2.setAge(25);
		user2.setEmail("rexlin700@gmail.com");

		try {
			userService.addUser(user2); // success
			// catch ex, but won't rollback
			int r = 1 / 0;
			log.info("==>  r is [{}]", r);
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