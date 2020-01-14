package xyz.rexlin600.mybatisplus.codegen.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;

import java.util.List;

/**
 * @author hekunlin
 */
public interface CodeService {

    /**
     * 分页查询表
     *
     * @param page      分页参数
     * @param tableName 数据库表名
     * @param id        数据源ID
     * @return
     */
    R<Page<TableMetaData>> page(Page page, String tableName, Long id);

    /**
     * 生成代码
     *
     * @param codeGenReq
     * @return
     */
    R generate(CodeGenReq codeGenReq);

}
