package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Sql method
 *
 * @author hekunlin
 */
@NoArgsConstructor
@Getter
public enum SqlMethodEnum {

	/**
	 * Insert one
	 */
	INSERT_ONE("insert", "插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
	/**
	 * Delete by id
	 */
	DELETE_BY_ID("deleteById", "根据ID 删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
	/**
	 * Delete by map
	 */
	DELETE_BY_MAP("deleteByMap", "根据columnMap 条件删除记录", "<script>\nDELETE FROM %s %s\n</script>"),
	/**
	 * Delete
	 */
	DELETE("delete", "根据 entity 条件删除记录", "<script>\nDELETE FROM %s %s %s\n</script>"),
	/**
	 * Delete batch by ids
	 */
	DELETE_BATCH_BY_IDS("deleteBatchIds", "根据ID集合，批量删除数据", "<script>\nDELETE FROM %s WHERE %s IN (%s)\n</script>"),
	/**
	 * Logic delete by id
	 */
	LOGIC_DELETE_BY_ID("deleteById", "根据ID 逻辑删除一条数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
	/**
	 * Logic delete by map sql method
	 */
	LOGIC_DELETE_BY_MAP("deleteByMap", "根据columnMap 条件逻辑删除记录", "<script>\nUPDATE %s %s %s\n</script>"),
	/**
	 * Logic delete sql method
	 */
	LOGIC_DELETE("delete", "根据 entity 条件逻辑删除记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
	/**
	 * Logic delete batch by ids
	 */
	LOGIC_DELETE_BATCH_BY_IDS("deleteBatchIds", "根据ID集合，批量逻辑删除数据", "<script>\nUPDATE %s %s WHERE %s IN (%s) %s\n</script>"),
	/**
	 * Update by id
	 */
	UPDATE_BY_ID("updateById", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
	/**
	 * Update sql method
	 */
	UPDATE("update", "根据 whereEntity 条件，更新记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
	/**
	 * Logic update by id
	 */
	LOGIC_UPDATE_BY_ID("updateById", "根据ID 修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
	/**
	 * Select by id
	 */
	SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s}"),
	/**
	 * Select by map
	 */
	SELECT_BY_MAP("selectByMap", "根据columnMap 查询一条数据", "<script>\nSELECT %s FROM %s %s\n</script>"),
	/**
	 * Select batch by ids
	 */
	SELECT_BATCH_BY_IDS("selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s)\n</script>"),
	/**
	 * Select one
	 */
	SELECT_ONE("selectOne", "查询满足条件一条数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Select count
	 */
	SELECT_COUNT("selectCount", "查询满足条件总记录数", "<script>\nSELECT COUNT(%s) FROM %s %s %s\n</script>"),
	/**
	 * Select list
	 */
	SELECT_LIST("selectList", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Select page
	 */
	SELECT_PAGE("selectPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Select maps
	 */
	SELECT_MAPS("selectMaps", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Select maps page
	 */
	SELECT_MAPS_PAGE("selectMapsPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Select objs
	 */
	SELECT_OBJS("selectObjs", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
	/**
	 * Logic select by id
	 */
	LOGIC_SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s} %s"),
	/**
	 * Logic select batch by ids
	 */
	LOGIC_SELECT_BATCH_BY_IDS("selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s) %s\n</script>");

	/**
	 * Method
	 */
	private String method;
	/**
	 * Desc
	 */
	private String desc;
	/**
	 * Sql
	 */
	private String sql;

	/**
	 * Sql method
	 *
	 * @param method method
	 * @param desc   desc
	 * @param sql    sql
	 */
	SqlMethodEnum(String method, String desc, String sql) {
		this.method = method;
		this.desc = desc;
		this.sql = sql;
	}

}
