package xyz.rexlin600.skywalking.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.skywalking.biz.UserBiz;
import xyz.rexlin600.skywalking.common.apiparam.PageResult;
import xyz.rexlin600.skywalking.common.apiparam.Response;
import xyz.rexlin600.skywalking.common.apiparam.ResponseGenerator;
import xyz.rexlin600.skywalking.model.User;

/**
 * User rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRest {

	/**
	 * User biz
	 */
	private final UserBiz userBiz;

	/**
	 * User rest
	 *
	 * @param userBiz user biz
	 */
	@Autowired
	public UserRest(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	/**
	 * Page page result
	 *
	 * @param page page
	 * @param size size
	 * @return the page result
	 */
	@GetMapping("/page")
	public PageResult<User> page(@RequestParam(value = "page") Integer page,
								 @RequestParam(value = "size") Integer size) {
		log.info("==>  page query");
		Page<User> pg = userBiz.page(new Page<>(page, size));

		// merge
		PageResult<User> objectPageResult = new PageResult<>();
		objectPageResult.setList(pg.getRecords());
		objectPageResult.setPage(page);
		objectPageResult.setSize(size);
		objectPageResult.setTotalCount(pg.getTotal());
		objectPageResult.setTotalPage(pg.getPages());

		return objectPageResult;
	}


	/**
	 * User response
	 *
	 * @param id id
	 * @return the response
	 */
	@GetMapping("/{id}")
	public Response user(@PathVariable(value = "id") Long id) {
		return ResponseGenerator.success(userBiz.getById(id));
	}


	/**
	 * Add response
	 *
	 * @param user user
	 * @return the response
	 */
	@PostMapping
	public Response add(@RequestBody User user) {
		userBiz.save(user);
		return ResponseGenerator.success();
	}


	/**
	 * Del response
	 *
	 * @param id id
	 * @return the response
	 */
	@DeleteMapping("/{id}")
	public Response del(@PathVariable(value = "id") Long id) {
		userBiz.removeById(id);
		return ResponseGenerator.success();
	}


	/**
	 * Upd response
	 *
	 * @param user user
	 * @return the response
	 */
	@PutMapping
	public Response upd(@RequestBody User user) {
		userBiz.updateById(user);
		return ResponseGenerator.success();
	}


}