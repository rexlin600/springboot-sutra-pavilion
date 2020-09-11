package xyz.rexlin600.mybatisplus.crud.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.crud.biz.UserBiz;
import xyz.rexlin600.mybatisplus.crud.common.apiparam.PageResult;
import xyz.rexlin600.mybatisplus.crud.common.apiparam.Response;
import xyz.rexlin600.mybatisplus.crud.common.apiparam.ResponseGenerator;
import xyz.rexlin600.mybatisplus.crud.model.User;

/**
 * MybatisPlus 用户接口
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
	 * 分页查询
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
	 * 查询用户详情
	 *
	 * @param id id
	 * @return the response
	 */
	@GetMapping("/{id}")
	public Response user(@PathVariable(value = "id") Long id) {
		User user = userBiz.getById(id);
		return ResponseGenerator.success(user);
	}


	/**
	 * 新增用户
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
	 * 删除用户
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
	 * 更新用户
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