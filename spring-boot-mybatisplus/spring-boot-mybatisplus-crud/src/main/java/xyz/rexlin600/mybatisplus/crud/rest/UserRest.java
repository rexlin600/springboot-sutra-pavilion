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
 * @menu MybatisPlus-CRUD
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRest {

    private final UserBiz userBiz;

    @Autowired
    public UserRest(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    /**
     * 1. 【用户分页】
     *
     * @param page
     * @param size
     * @return
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
     * 2. 【根据ID查询】
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response user(@PathVariable(value = "id") Long id) {
        return ResponseGenerator.success(userBiz.getById(id));
    }


    /**
     * 3. 【新增用户】
     *
     * @param user
     * @return
     */
    @PostMapping
    public Response add(@RequestBody User user) {
        userBiz.save(user);
        return ResponseGenerator.success();
    }


    /**
     * 4. 【删除用户】
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response del(@PathVariable(value = "id") Long id) {
        userBiz.removeById(id);
        return ResponseGenerator.success();
    }


    /**
     * 5. 【更新用户】
     *
     * @param user
     * @return
     */
    @PutMapping
    public Response upd(@RequestBody User user) {
        userBiz.updateById(user);
        return ResponseGenerator.success();
    }


}