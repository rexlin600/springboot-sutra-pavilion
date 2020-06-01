package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

/**
 * 检查 Boolean 请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooleanReq implements Serializable {

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
}