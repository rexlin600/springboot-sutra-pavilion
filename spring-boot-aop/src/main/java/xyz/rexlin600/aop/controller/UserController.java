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
 * AOP-用户操作
 *
 * @author rexlin600
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 1. 分页列表
     *
     * @param page 页码
     * @param size 每页条数
     * @return {@link R} {@link R}
     */
    @GetMapping("/page")
    public R page(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return R.ok(userService.page(new Page<>(page, size)));
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return {@link R}
     */
    @PostMapping
    public R add(@RequestBody User user) {
        return R.ok(userService.save(user));
    }

    /**
     * 删除用户
     *
     * @param id ID
     * @return {@link R}
     */
    @DeleteMapping("/{id}")
    public R del(@PathVariable(value = "id") Long id) {
        return R.ok(userService.removeById(id));
    }

    /**
     * 查询用户
     *
     * @param id ID
     * @return {@link R}
     */
    @SysAopLog(value = "查询用户")
    @GetMapping("/{id}")
    public R get(@PathVariable(value = "id") Long id) {
        return R.ok(userService.getById(id));
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return {@link R}
     */
    @PutMapping
    public R update(@RequestBody User user) {
        return R.ok(userService.updateById(user));
    }

}

