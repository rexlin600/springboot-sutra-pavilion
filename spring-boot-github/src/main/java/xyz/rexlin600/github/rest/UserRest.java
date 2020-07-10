package xyz.rexlin600.github.rest;

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.Key;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.github.common.apiparam.Response;
import xyz.rexlin600.github.common.apiparam.ResponseGenerator;
import xyz.rexlin600.github.config.runner.GithubRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * User rest
 *
 * @author hekunlin
 */
@RestController
@RequestMapping(value = "/user")
public class UserRest {

	/**
	 * Info response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/info")
	public Response info(@RequestParam(value = "username", required = false) String username) {
		User user = null;
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			user = GithubRunner.userService.getUser();
		} else {
			user = GithubRunner.userService.getUser(username);
		}
		return ResponseGenerator.success(user);
	}


	/**
	 * Add email response
	 *
	 * @param email email
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/email")
	public Response addEmail(@RequestParam(value = "email") String email) {
		// resolve space
		if (!StringUtils.isEmpty(email)) {
			email = email.trim();
		}

		GithubRunner.userService.addEmail(email);
		return ResponseGenerator.success();
	}


	/**
	 * Remove email response
	 *
	 * @param email email
	 * @return the response
	 */
	@SneakyThrows
	@DeleteMapping("/email")
	public Response removeEmail(@RequestParam(value = "email") String email) {
		// resolve space
		if (!StringUtils.isEmpty(email)) {
			email = email.trim();
		}

		GithubRunner.userService.removeEmail(email);
		return ResponseGenerator.success();
	}


	/**
	 * Followers response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/followers")
	public Response followers(@RequestParam(value = "username", required = false) String username) {
		List<User> list = null;
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			// 查询自己的 followers
			list = GithubRunner.userService.getFollowers();
		} else {
			// 查询指定用户的 followers
			list = GithubRunner.userService.getFollowers(username);
		}
		return ResponseGenerator.success(list);
	}


	/**
	 * Page followers response
	 *
	 * @param username username
	 * @param start    start
	 * @param end      end
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/page/followers")
	public Response pageFollowers(@RequestParam(value = "username", required = false) String username,
								  @RequestParam(value = "start", defaultValue = "1") Integer start,
								  @RequestParam(value = "end", defaultValue = "10") Integer end) {
		List<User> list = new ArrayList();
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			// 查询自己的 followers
			PageIterator<User> p1 = GithubRunner.userService.pageFollowers(start, end);
			if (p1.hasNext()) {
				Collection<User> userCollection = p1.next();
				list.addAll(userCollection);
			}
		} else {
			// 查询指定用户的 followers
			PageIterator<User> p2 = GithubRunner.userService.pageFollowers(username, start, end);
			if (p2.hasNext()) {
				Collection<User> userCollection = p2.next();
				list.addAll(userCollection);
			}
		}
		return ResponseGenerator.success(list);
	}


	/**
	 * Page followers response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/following")
	public Response pageFollowers(@RequestParam(value = "username", required = false) String username) {
		List<User> list = new ArrayList();
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			list = GithubRunner.userService.getFollowing();
		} else {
			// 查询指定用户的 following
			list = GithubRunner.userService.getFollowing(username);
		}
		return ResponseGenerator.success(list);
	}


	/**
	 * Page following response
	 *
	 * @param username username
	 * @param start    start
	 * @param end      end
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/page/following")
	public Response pageFollowing(@RequestParam(value = "username", required = false) String username,
								  @RequestParam(value = "start", defaultValue = "1") Integer start,
								  @RequestParam(value = "end", defaultValue = "10") Integer end) {
		List<User> list = new ArrayList();
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		if (StringUtils.isEmpty(username)) {
			// 查询自己的 following
			PageIterator<User> p1 = GithubRunner.userService.pageFollowing(start, end);
			if (p1.hasNext()) {
				Collection<User> userCollection = p1.next();
				list.addAll(userCollection);
			}
		} else {
			// 查询指定用户的 following
			PageIterator<User> p2 = GithubRunner.userService.pageFollowing(username, start, end);
			if (p2.hasNext()) {
				Collection<User> userCollection = p2.next();
				list.addAll(userCollection);
			}
		}
		return ResponseGenerator.success(list);
	}


	/**
	 * Follow response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@PutMapping("/follow")
	public Response follow(@RequestParam(value = "username") String username) {
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		GithubRunner.userService.follow(username);
		return ResponseGenerator.success();
	}


	/**
	 * Unfollow response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@PutMapping("/unfollow")
	public Response unfollow(@RequestParam(value = "username") String username) {
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		GithubRunner.userService.unfollow(username);
		return ResponseGenerator.success();
	}


	/**
	 * Is follow response
	 *
	 * @param username username
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/isFollow")
	public Response isFollow(@RequestParam(value = "username") String username) {
		// resolve space
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
		}

		boolean following = GithubRunner.userService.isFollowing(username);
		return ResponseGenerator.success(following);
	}


	/**
	 * Keys response
	 *
	 * @return the response
	 */
	@SneakyThrows
	@GetMapping("/keys")
	public Response keys() {
		List<Key> keys = GithubRunner.userService.getKeys();
		return ResponseGenerator.success(keys);
	}

	/**
	 * Create key response
	 *
	 * @param key key
	 * @return the response
	 */
	@SneakyThrows
	@PostMapping("/key")
	public Response createKey(@RequestBody Key key) {
		Key key1 = GithubRunner.userService.createKey(key);
		return ResponseGenerator.success(key1);
	}

	/**
	 * Keys response
	 *
	 * @param id id
	 * @return the response
	 */
	@SneakyThrows
	@DeleteMapping("/key/{id}")
	public Response keys(@PathVariable(value = "id") Integer id) {
		GithubRunner.userService.deleteKey(id);
		return ResponseGenerator.success();
	}


}