package xyz.rexlin600.mybatisplus.codegen.rest;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.codegen.common.constant.CodeGenConstant;
import xyz.rexlin600.mybatisplus.codegen.common.req.DataSourceReq;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;
import xyz.rexlin600.mybatisplus.codegen.service.DataSourceService;
import xyz.rexlin600.mybatisplus.codegen.util.AesUtils;

import java.util.Date;


/**
 * Data source rest
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/ds")
public class DataSourceRest {

	/**
	 * Data source service
	 */
	private final DataSourceService dataSourceService;

	/**
	 * Data source rest
	 *
	 * @param dataSourceService data source service
	 */
	@Autowired
	public DataSourceRest(DataSourceService dataSourceService) {
		this.dataSourceService = dataSourceService;
	}


	/**
	 * Save data source
	 *
	 * @param dataSourceReq data source req
	 * @return the r
	 */
	@PostMapping
	public R saveDataSource(@RequestBody DataSourceReq dataSourceReq) {
		DataSource source = new DataSource();
		source = BeanCopier.create(dataSourceReq, source, new CopyOptions().setIgnoreNullValue(true)).copy();
		source.setCreateTime(new Date());
		// AES加密
		source.setUsername(AesUtils.encrypt(dataSourceReq.getUsername()));
		source.setPassword(AesUtils.encrypt(dataSourceReq.getPassword()));
		dataSourceService.save(source);

		return R.ok(CodeGenConstant.SUCCESS);
	}

	/**
	 * Delete data source
	 *
	 * @param id id
	 * @return the r
	 */
	@DeleteMapping("/{id}")
	public R deleteDataSource(@PathVariable(value = "id") Long id) {
		dataSourceService.removeById(id);
		return R.ok(CodeGenConstant.SUCCESS);
	}

	/**
	 * Update data source
	 *
	 * @param dataSourceReq data source req
	 * @return the r
	 */
	@PutMapping
	public R updateDataSource(@RequestBody DataSourceReq dataSourceReq) {
		DataSource source = new DataSource();
		// AES加密
		dataSourceReq.setUsername(AesUtils.encrypt(dataSourceReq.getUsername()));
		dataSourceReq.setPassword(AesUtils.encrypt(dataSourceReq.getPassword()));

		source = BeanCopier.create(dataSourceReq, source, new CopyOptions().setIgnoreNullValue(true)).copy();
		source.setUpdateTime(new Date());
		dataSourceService.updateById(source);

		return R.ok(CodeGenConstant.SUCCESS);
	}

	/**
	 * Page data source
	 *
	 * @param page page
	 * @param size size
	 * @return the r
	 */
	@GetMapping("/page")
	public R pageDataSource(@RequestParam(value = "page") Integer page,
							@RequestParam(value = "size") Integer size) {
		Page<DataSource> sourcePage = dataSourceService.page(new Page<DataSource>(page, size));

		return R.ok(sourcePage);
	}

	/**
	 * Gets data source
	 *
	 * @param id id
	 * @return the data source
	 */
	@GetMapping("/{id}")
	public R getDataSource(@PathVariable(value = "id") Long id) {
		LambdaQueryWrapper<DataSource> queryWrapper = new LambdaQueryWrapper<DataSource>()
				.eq(true, DataSource::getId, id);
		DataSource dataSource = dataSourceService.getOne(queryWrapper);

		return R.ok(dataSource);
	}

}