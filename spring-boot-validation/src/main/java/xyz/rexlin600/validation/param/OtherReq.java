package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * 其他检查 请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherReq implements Serializable {

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

    /**
     * URL 校验
     */
    //@org.hibernate.validator.constraints.URL(protocol = "https", host = "www.baidu.com", port = -1)
    //private URL url;

}