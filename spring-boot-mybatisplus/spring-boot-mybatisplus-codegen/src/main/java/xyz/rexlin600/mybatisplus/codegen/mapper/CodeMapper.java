package xyz.rexlin600.mybatisplus.codegen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Code mapper
 *
 * @author hekunlin
 */
public interface CodeMapper {

	/**
	 * Query list page
	 *
	 * @param page      page
	 * @param tableName table name
	 * @return the page
	 */
	@Deprecated
	@SuppressWarnings("MybatisMapperMethodInspection")
	IPage<List<Map<String, Object>>> queryList(Page page, @Param("tableName") String tableName);

}
