package xyz.rexlin600.swagger2.controller;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.rexlin600.swagger2.config.BaseResult;
import xyz.rexlin600.swagger2.model.User;

import java.util.*;


/**
 * User controller
 *
 * @author hekunlin
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	/**
	 * users
	 */
	private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

	/**
	 * Gets user list *
	 *
	 * @return the user list
	 */
	@GetMapping
	public List<User> getUserList() {
		return new ArrayList<>(users.values());
	}

	/**
	 * Post user base result
	 *
	 * @param user user
	 * @return the base result
	 */
	@PostMapping
	public BaseResult<User> postUser(@ApiIgnore User user) {
		users.put(user.getId(), user);
		return BaseResult.successWithData(user);
	}

	/**
	 * Gets user *
	 *
	 * @param id id
	 * @return the user
	 */
	@GetMapping(value = "/{id}")
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}

	/**
	 * Put user base result
	 *
	 * @param id   id
	 * @param user user
	 * @return the base result
	 */
	@PutMapping(value = "/{id}")
	public BaseResult<User> putUser(@PathVariable Long id, @ApiIgnore User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return BaseResult.successWithData(u);
	}

	/**
	 * Delete user string
	 *
	 * @param id id
	 * @return the string
	 */
	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

	/**
	 * Ignore me string
	 *
	 * @param id id
	 * @return the string
	 */
	@DeleteMapping(value = "/ignoreMe/{id}")
	public String ignoreMe(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}
}