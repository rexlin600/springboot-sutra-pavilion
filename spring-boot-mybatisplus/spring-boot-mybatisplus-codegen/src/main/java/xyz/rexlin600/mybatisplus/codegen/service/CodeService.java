package xyz.rexlin600.mybatisplus.codegen.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;

import java.sql.SQLException;

/**
 * @author hekunlin
 */
public interface CodeService {

    /**
     * 分页查询表
     *
     * @param page      分页参数
     * @param size      分页参数
     * @param tableName 数据库表名
     * @param id        数据源ID
     * @return
     * @throws SQLException
     */
    R<Page<TableMetaData>> page(Integer page, Integer size, String tableName, Long id) throws SQLException;

    /**
     * 生成代码
     *
     * @param codeGenReq
     * @return
     */
    R generate(CodeGenReq codeGenReq);

}
