package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 长度检查
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LengthReq implements Serializable {

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

}