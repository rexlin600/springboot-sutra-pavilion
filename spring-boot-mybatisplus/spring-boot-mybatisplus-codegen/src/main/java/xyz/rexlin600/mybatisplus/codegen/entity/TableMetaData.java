package xyz.rexlin600.mybatisplus.codegen.entity;

import lombok.*;

/**
 * TableMetaData 实体类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TableMetaData {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 表引擎
     */
    private String engine;

    /**
     * 表字符集
     */
    private String tableCollation;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}