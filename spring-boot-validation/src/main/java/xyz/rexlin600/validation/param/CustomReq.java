package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rexlin600.validation.validator.Phone;

import java.io.Serializable;

/**
 * 自定义检查请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomReq implements Serializable {

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