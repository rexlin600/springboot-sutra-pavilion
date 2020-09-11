package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;

import java.sql.SQLException;

/**
 * MybatisPlus 代码生成接口
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/code")
@Validated
public class CodeGenerateRest {

	/**
	 * Code service
	 */
	private final CodeService codeService;

	/**
	 * Code generate rest
	 *
	 * @param codeService code service
	 */
	@Autowired
	public CodeGenerateRest(CodeService codeService) {
		this.codeService = codeService;
	}

	/**
	 * 数据源分页查询
	 *
	 * @param page      page
	 * @param size      size
	 * @param tableName table name
	 * @param id        id
	 * @return the page
	 */
	@GetMapping("/page")
	public R<Page<TableMetaData>> getPage(@RequestParam(value = "page") Integer page,
										  @RequestParam(value = "size") Integer size,
										  @RequestParam(value = "tableName", required = false) String tableName,
										  @RequestParam(value = "id") Long id) {
		try {
			return codeService.page(page, size, tableName, id);
		} catch (SQLException e) {
			return R.failed(e.getMessage());
		}
	}


	/**
	 * 生成代码
	 *
	 * @param codeGenReq code gen req
	 * @return the r
	 */
	@PostMapping("/generate")
	public R generate(@Validated @RequestBody CodeGenReq codeGenReq) {
		return codeService.generate(codeGenReq);
	}

}
