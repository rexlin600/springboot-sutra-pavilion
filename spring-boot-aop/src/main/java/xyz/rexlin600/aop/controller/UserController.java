package xyz.rexlin600.aop.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.api.ApiController;
import xyz.rexlin600.aop.aspect.annotation.annotation.SysAopLog;
import xyz.rexlin600.aop.entity.User;
import xyz.rexlin600.aop.service.UserService;

/**
 * @author rexlin600
 * @menu AOP-用户操作
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
     * 1. 【分页列表】
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    public R page(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return R.ok(userService.page(new Page<>(page, size)));
    }

    /**
     * 2. 【新增用户】
     *
     * @param user
     * @return
     */
    @PostMapping
    public R add(@RequestBody User user) {
        return R.ok(userService.save(user));
    }

    /**
     * 3. 【删除用户】
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R del(@PathVariable(value = "id") Long id) {
        return R.ok(userService.removeById(id));
    }

    /**
     * 4. 【查询用户】
     *
     * @param id
     * @return
     */
    @SysAopLog(value = "查询用户")
    @GetMapping("/{id}")
    public R get(@PathVariable(value = "id") Long id) {
        return R.ok(userService.getById(id));
    }

    /**
     * 5. 【修改用户】
     *
     * @param user
     * @return
     */
    @PutMapping
    public R update(@RequestBody User user) {
        return R.ok(userService.updateById(user));
    }

}

