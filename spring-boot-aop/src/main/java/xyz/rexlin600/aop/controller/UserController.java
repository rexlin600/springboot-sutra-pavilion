package xyz.rexlin600.aop.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.aop.aspect.annotation.annotation.SysAopLog;
import xyz.rexlin600.aop.entity.User;
import xyz.rexlin600.aop.service.UserService;

/**
 * User controller
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController {

	/**
	 * User service
	 */
	private final UserService userService;

	/**
	 * User controller
	 *
	 * @param userService user service
	 */
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Page r
	 *
	 * @param page page
	 * @param size size
	 * @return the r
	 */
	@GetMapping("/page")
	public R page(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return R.ok(userService.page(new Page<>(page, size)));
	}

	/**
	 * Add r
	 *
	 * @param user user
	 * @return the r
	 */
	@PostMapping
	public R add(@RequestBody User user) {
		return R.ok(userService.save(user));
	}

	/**
	 * Del r
	 *
	 * @param id id
	 * @return the r
	 */
	@DeleteMapping("/{id}")
	public R del(@PathVariable(value = "id") Long id) {
		return R.ok(userService.removeById(id));
	}

	/**
	 * Get r
	 *
	 * @param id id
	 * @return the r
	 */
	@SysAopLog(value = "查询用户")
	@GetMapping("/{id}")
	public R get(@PathVariable(value = "id") Long id) {
		return R.ok(userService.getById(id));
	}

	/**
	 * Update r
	 *
	 * @param user user
	 * @return the r
	 */
	@PutMapping
	public R update(@RequestBody User user) {
		return R.ok(userService.updateById(user));
	}

}

