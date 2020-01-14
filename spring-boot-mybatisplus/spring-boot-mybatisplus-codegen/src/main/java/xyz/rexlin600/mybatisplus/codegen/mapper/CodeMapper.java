package xyz.rexlin600.mybatisplus.codegen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
 * @author hekunlin
 */
public interface CodeMapper {

    /**
     * 分页查询表格
     *
     * @param page
     * @param tableName
     * @return
     */
    @SuppressWarnings("MybatisMapperMethodInspection")
    IPage<List<Map<String, Object>>> queryList(Page page, @Param("tableName") String tableName);

}
