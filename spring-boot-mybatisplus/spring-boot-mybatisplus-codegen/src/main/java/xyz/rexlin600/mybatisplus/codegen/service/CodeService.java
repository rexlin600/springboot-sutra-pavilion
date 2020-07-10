package xyz.rexlin600.mybatisplus.codegen.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;

import java.sql.SQLException;

/**
 * Code service
 *
 * @author hekunlin
 */
public interface CodeService {

	/**
	 * Page r
	 *
	 * @param page      page
	 * @param size      size
	 * @param tableName table name
	 * @param id        id
	 * @return the r
	 * @throws SQLException sql exception
	 */
	R<Page<TableMetaData>> page(Integer page, Integer size, String tableName, Long id) throws SQLException;

	/**
	 * Generate r
	 *
	 * @param codeGenReq code gen req
	 * @return the r
	 */
	R generate(CodeGenReq codeGenReq);

}
