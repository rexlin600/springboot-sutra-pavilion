package xyz.rexlin600.aop.rest;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * AOP 系统日志接口
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController extends ApiController {

	/**
	 * Sys log service
	 */
	private final SysLogService sysLogService;

	/**
	 * Sys log controller
	 *
	 * @param sysLogService sys log service
	 */
	@Autowired
	public SysLogController(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	/**
	 * 分页查询
	 *
	 * @param page page
	 * @param size size
	 * @return the r
	 */
	@GetMapping("/page")
	public R page(@RequestParam(value = "page", defaultValue = "1") Integer page,
				  @RequestParam(value = "size", defaultValue = "10") Integer size) {
		return R.ok(sysLogService.page(new Page<>(page, size)));
	}

	/**
	 * 新增日志
	 *
	 * @param sysLog sys log
	 * @return the r
	 */
	@PostMapping
	public R add(@RequestBody SysLog sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}

	/**
	 * 删除日志
	 *
	 * @param id id
	 * @return the r
	 */
	@DeleteMapping("/{id}")
	public R del(@PathVariable(value = "id") Long id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * 查询日志
	 *
	 * @param id id
	 * @return the r
	 */
	@GetMapping("/{id}")
	public R get(@PathVariable(value = "id") Long id) {
		return R.ok(sysLogService.getById(id));
	}

	/**
	 * 更新日志
	 *
	 * @param sysLog sys log
	 * @return the r
	 */
	@PutMapping
	public R update(@RequestBody SysLog sysLog) {
		return R.ok(sysLogService.updateById(sysLog));
	}

}

