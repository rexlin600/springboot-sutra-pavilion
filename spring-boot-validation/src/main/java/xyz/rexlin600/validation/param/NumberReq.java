package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 数值检查
 *
 * @author: hekunlin
 * @since: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberReq implements Serializable {

    // -----------------------------------------------------------------------------------------------
    // 数值检查
    // -----------------------------------------------------------------------------------------------

    /**
     * 距离
     *
     * @Min 用于校验 Number 和 String 对象是否大等于指定的值
     * @Max 用于校验 Number 和 String 对象是否大等于指定的值
     */
    @Min(value = 0, message = "参数错误：距离最小为0")
    @Max(value = 1000, message = "参数错误：距离最大为1000")
    private Integer distance;

    /**
     * 学校
     *
     * @Min 用于校验 Number 和 String 对象是否大等于指定的值
     * @Max 用于校验 Number 和 String 对象是否大等于指定的值
     */
    @Min(value = 2, message = "参数错误：学校名称字符最小为2")
    @Max(value = 100, message = "参数错误：学校名称字符最大为100")
    private String school;

    /**
     * 重量
     *
     * @DecimalMin 用于校验 通过BigDecimal定义的最大值的字符串表示
     * @DecimalMax 用于校验 通过BigDecimal定义的最大值的字符串表示
     */
    @DecimalMin(value = "0.01", message = "参数错误：重量最少0.01")
    @DecimalMax(value = "999.99", message = "参数错误：重量最大999.99")
    private BigDecimal weight;

    /**
     * 得分
     *
     * @Digits 用于校验 Number、String 的构成是否合法
     */
    @Digits(integer = 3, fraction = 2, message = "参数错误：整数部分最大3位、小数部分最大2位")
    private Double grade;

}