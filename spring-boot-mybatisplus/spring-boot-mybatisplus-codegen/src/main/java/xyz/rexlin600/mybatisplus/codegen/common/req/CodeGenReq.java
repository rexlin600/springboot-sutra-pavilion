package xyz.rexlin600.mybatisplus.codegen.common.req;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * CodeGenReq 请求类
 *
 * @author: hekunlin
 * @since: 2020/1/14
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
    @Builder.Default
    private String author = "rexlin600";

    /**
     * 是否开启 Swagger2
     */
    @Builder.Default
    private Boolean openApiDoc = false;

    // -----------------------------------------------------------------------------------------------
    // 各层代码位置
    // -----------------------------------------------------------------------------------------------

    /**
     * entity/do 包位置
     */
    @NotBlank
    private String entityPath;

    /**
     * mapper 包位置
     */
    @NotBlank
    private String mapperPath;

    /**
     * service 包位置
     */
    @NotBlank
    private String svcPath;

    /**
     * serviceImpl 包位置
     */
    @NotBlank
    private String svcImplPath;

    /**
     * controller 包位置
     */
    @NotBlank
    private String restPath;

    /**
     * XML 包位置
     */
    @NotBlank
    private String xmlPath;

    // -----------------------------------------------------------------------------------------------
    // 其余配置
    // -----------------------------------------------------------------------------------------------

    /**
     * 表前缀
     */
    private String prefix;

    /**
     * 乐观锁字段
     */
    private String versionColumn;

    /**
     * 是否开启 lombok
     */
    @Builder.Default
    private boolean lombok = true;

    /**
     * 逻辑删除字段
     */
    private String logicColumn;

    // -----------------------------------------------------------------------------------------------
    // 要生成的表 LIST
    // -----------------------------------------------------------------------------------------------

    /**
     * 要生成的表的列表
     */
    @NotEmpty
    private List<String> list;


}