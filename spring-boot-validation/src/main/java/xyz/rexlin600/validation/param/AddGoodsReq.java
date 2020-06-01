package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import xyz.rexlin600.validation.validator.Phone;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 新增 Goods 请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGoodsReq implements Serializable {

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
     * 序号
     *
     * @NotNull 用于校验 基本数据类型
     */
    @NotNull(message = "参数错误：序号不可为空")
    private Integer sort;

    /**
     * 额外请求 List
     *
     * @NotEmpty 用于校验 集合类型
     */
    @Valid
    @NotEmpty(message = "参数错误：列表不可为空")
    private List<AddGoodsInnerReq> list;

    // -----------------------------------------------------------------------------------------------
    // Boolean 检查
    // -----------------------------------------------------------------------------------------------

    /**
     * 富有
     *
     * @AssertTrue 用于校验 Boolean 对象是否为 True
     */
    @AssertTrue(message = "参数错误：您必须富有")
    private Boolean rich;

    /**
     * 英俊
     *
     * @AssertTrue 用于校验 Boolean 对象是否为 True
     */
    @AssertFalse(message = "参数错误：您并不英俊")
    private Boolean handSome;

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

    // -----------------------------------------------------------------------------------------------
    // 长度检查
    // -----------------------------------------------------------------------------------------------

    /**
     * 内容
     *
     * @Length 用于校验 String
     */
    @Length(message = "参数错误：内容字符不得超过20", min = 0, max = 20)
    private String content;

    /**
     * 集合
     *
     * @Size 用于校验（Array,Collection,Map,String）长度是否在给定的范围之内
     */
    @Size(min = 1, max = 2, message = "参数错误：集合长度范围为 [1,2]")
    private List arrayList;

    /**
     * 爱好
     *
     * @Size 用于校验（Array,Collection,Map,String）长度是否在给定的范围之内
     */
    @Size(min = 2, max = 20, message = "参数错误：爱好字符串范围为 [2,20]")
    private String hobby;

    // -----------------------------------------------------------------------------------------------
    // 其他校验
    // -----------------------------------------------------------------------------------------------

    /**
     * 邮箱
     *
     * @Email 用于校验 邮箱是否合法
     */
    @Email(message = "参数错误：邮箱格式不正确")
    private String email;

    /**
     * 信用卡
     *
     * @CreditCardNumber 用于校验 信用卡是否合法
     */
    @CreditCardNumber(message = "参数错误：信用卡格式不合法")
    private String creditCard;


    // -----------------------------------------------------------------------------------------------
    // 自定义校验
    // -----------------------------------------------------------------------------------------------

    /**
     * 电话
     *
     * @Phone 自定义校验规器、用于校验 手机号是否合法
     */
    @Phone(message = "参数错误：手机号格式错误")
    private String phone;

}