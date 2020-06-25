package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 数据库方法关键字枚举类
 *
 * @author hekunlin
 */

@NoArgsConstructor
@Getter
public enum SqlMethod {

    /**
     * insert
     */
    INSERT_ONE("insert", "插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    /**
     * deleteById
     */
    DELETE_BY_ID("deleteById", "根据ID 删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
    /**
     * deleteByMap
     */
    DELETE_BY_MAP("deleteByMap", "根据columnMap 条件删除记录", "<script>\nDELETE FROM %s %s\n</script>"),
    /**
     * delete
     */
    DELETE("delete", "根据 entity 条件删除记录", "<script>\nDELETE FROM %s %s %s\n</script>"),
    /**
     * deleteBatchIds
     */
    DELETE_BATCH_BY_IDS("deleteBatchIds", "根据ID集合，批量删除数据", "<script>\nDELETE FROM %s WHERE %s IN (%s)\n</script>"),
    /**
     * deleteById
     */
    LOGIC_DELETE_BY_ID("deleteById", "根据ID 逻辑删除一条数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    /**
     * deleteByMap
     */
    LOGIC_DELETE_BY_MAP("deleteByMap", "根据columnMap 条件逻辑删除记录", "<script>\nUPDATE %s %s %s\n</script>"),
    /**
     * delete
     */
    LOGIC_DELETE("delete", "根据 entity 条件逻辑删除记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
    /**
     * deleteBatchIds
     */
    LOGIC_DELETE_BATCH_BY_IDS("deleteBatchIds", "根据ID集合，批量逻辑删除数据", "<script>\nUPDATE %s %s WHERE %s IN (%s) %s\n</script>"),
    /**
     * updateById
     */
    UPDATE_BY_ID("updateById", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    /**
     * update
     */
    UPDATE("update", "根据 whereEntity 条件，更新记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
    /**
     * updateById
     */
    LOGIC_UPDATE_BY_ID("updateById", "根据ID 修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    /**
     * selectById
     */
    SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s}"),
    /**
     * selectByMap
     */
    SELECT_BY_MAP("selectByMap", "根据columnMap 查询一条数据", "<script>\nSELECT %s FROM %s %s\n</script>"),
    /**
     * selectBatchIds
     */
    SELECT_BATCH_BY_IDS("selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s)\n</script>"),
    /**
     * selectOne
     */
    SELECT_ONE("selectOne", "查询满足条件一条数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectCount
     */
    SELECT_COUNT("selectCount", "查询满足条件总记录数", "<script>\nSELECT COUNT(%s) FROM %s %s %s\n</script>"),
    /**
     * selectList
     */
    SELECT_LIST("selectList", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectPage
     */
    SELECT_PAGE("selectPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectMaps
     */
    SELECT_MAPS("selectMaps", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectMapsPage
     */
    SELECT_MAPS_PAGE("selectMapsPage", "查询满足条件所有数据（并翻页）", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectObjs
     */
    SELECT_OBJS("selectObjs", "查询满足条件所有数据", "<script>\nSELECT %s FROM %s %s %s\n</script>"),
    /**
     * selectById
     */
    LOGIC_SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s} %s"),
    /**
     * selectBatchIds
     */
    LOGIC_SELECT_BATCH_BY_IDS("selectBatchIds", "根据ID集合，批量查询数据", "<script>\nSELECT %s FROM %s WHERE %s IN (%s) %s\n</script>");

    /**
     * 方法
     */
    private String method;
    /**
     * 描述
     */
    private String desc;
    /**
     * SQL脚本
     */
    private String sql;

    SqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

}
