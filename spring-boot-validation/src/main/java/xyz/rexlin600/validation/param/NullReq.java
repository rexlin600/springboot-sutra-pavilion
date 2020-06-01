package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * 检查空 请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NullReq implements Serializable {

    // -----------------------------------------------------------------------------------------------
    // 空检查
    // -----------------------------------------------------------------------------------------------

    /**
     * ID
     *
     * @Null 用于校验 基本数据类型
     */
    @Null(message = "ID必须为空")
    private Long id;

    /**
     * 名称
     *
     * @NotBlank 用于校验 String
     */
    @NotBlank(message = "参数错误：名称不可为空")
    private String name;

    /**
     * 序
     *
     * @NotNull 用于校验 基本数据类型
     */
    @NotNull(message = "参数错误：序号不可为空")
    private Integer sort;

    /**
     * 额外请求 List
     *
     * @NotEmpty 用于校验 集合类型
     * @Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
     */
    @Valid
    @NotEmpty(message = "参数错误：列表不可为空")
    private List<NullInnerReq> list;

}