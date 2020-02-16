package xyz.rexlin600.aop.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.api.ApiController;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * @author rexlin600
 * @menu AOP-系统日志
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
     * 1. 【分页列表】
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    public R page(@RequestParam(value = "page", defaultValue = "1") Integer page,
                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return R.ok(sysLogService.page(new Page<>(page, size)));
    }

    /**
     * 2. 【新增系统日志】
     *
     * @param SysLog
     * @return
     */
    @PostMapping
    public R add(@RequestBody SysLog SysLog) {
        return R.ok(sysLogService.save(SysLog));
    }

    /**
     * 3. 【删除系统日志】
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R del(@PathVariable(value = "id") Long id) {
        return R.ok(sysLogService.removeById(id));
    }

    /**
     * 4. 【查询系统日志】
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R get(@PathVariable(value = "id") Long id) {
        return R.ok(sysLogService.getById(id));
    }

    /**
     * 5. 【修改系统日志】
     *
     * @param SysLog
     * @return
     */
    @PutMapping
    public R update(@RequestBody SysLog SysLog) {
        return R.ok(sysLogService.updateById(SysLog));
    }

}

