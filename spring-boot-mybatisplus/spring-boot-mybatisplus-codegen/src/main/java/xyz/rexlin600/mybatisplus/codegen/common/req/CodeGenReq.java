package xyz.rexlin600.mybatisplus.codegen.common.req;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * CodeGenReq 请求类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CodeGenReq implements Serializable {

    /**
     * 数据源ID
     */
    @NotNull
    private Long id;

    /**
     * 用户
     */
    @NotEmpty
    private String author;

    /**
     * 是否开启 Swagger2
     */
    private Boolean openApiDoc;

    /**
     * 生成的代码包名称
     */
    @NotEmpty
    private String packageName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 乐观锁字段
     */
    private String versionColumn;

    /**
     * 逻辑删除字段
     */
    private String logicColumn;

    /**
     * 要生成的表的列表
     */
    @NotNull
    private List<String> list;


}