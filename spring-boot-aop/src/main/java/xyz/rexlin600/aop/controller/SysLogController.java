package xyz.rexlin600.aop.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * Sys log controller
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
	 * Page r
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
	 * Add r
	 *
	 * @param sysLog sys log
	 * @return the r
	 */
	@PostMapping
	public R add(@RequestBody SysLog sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}

	/**
	 * Del r
	 *
	 * @param id id
	 * @return the r
	 */
	@DeleteMapping("/{id}")
	public R del(@PathVariable(value = "id") Long id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * Get r
	 *
	 * @param id id
	 * @return the r
	 */
	@GetMapping("/{id}")
	public R get(@PathVariable(value = "id") Long id) {
		return R.ok(sysLogService.getById(id));
	}

	/**
	 * Update r
	 *
	 * @param sysLog sys log
	 * @return the r
	 */
	@PutMapping
	public R update(@RequestBody SysLog sysLog) {
		return R.ok(sysLogService.updateById(sysLog));
	}

}

