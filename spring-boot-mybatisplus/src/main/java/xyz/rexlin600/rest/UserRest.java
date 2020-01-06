package xyz.rexlin600.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.biz.UserBiz;
import xyz.rexlin600.common.apiparam.PageResult;
import xyz.rexlin600.model.User;

/**
 * @menu MybatisPlus_用户API
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

    // TODO 根据ID查询
    // TODO 根据条件查询
    // TODO 新增
    // TODO 删除
    // TODO 修改

}