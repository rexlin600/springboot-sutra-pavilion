package xyz.rexlin600.aop.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * AOP-系统日志
 *
 * @author rexlin600
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController extends ApiController {

    private final SysLogService sysLogService;

    @Autowired
    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    /**
     * 分页列表
     *
     * @param page 页码
     * @param size 每页条数
     * @return {@link R} {@link R}
     */
    @GetMapping("/page")
    public R page(@RequestParam(value = "page", defaultValue = "1") Integer page,
                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return R.ok(sysLogService.page(new Page<>(page, size)));
    }

    /**
     * 新增系统日志
     *
     * @param sysLog 系统日志
     * @return {@link R} {@link R}
     */
    @PostMapping
    public R add(@RequestBody SysLog sysLog) {
        return R.ok(sysLogService.save(sysLog));
    }

    /**
     * 删除系统日志
     *
     * @param id ID
     * @return {@link R} {@link R}
     */
    @DeleteMapping("/{id}")
    public R del(@PathVariable(value = "id") Long id) {
        return R.ok(sysLogService.removeById(id));
    }

    /**
     * 查询系统日志
     *
     * @param id ID
     * @return {@link R}
     */
    @GetMapping("/{id}")
    public R get(@PathVariable(value = "id") Long id) {
        return R.ok(sysLogService.getById(id));
    }

    /**
     * 修改系统日志
     *
     * @param sysLog 系统日志
     * @return {@link R}
     */
    @PutMapping
    public R update(@RequestBody SysLog sysLog) {
        return R.ok(sysLogService.updateById(sysLog));
    }

}

