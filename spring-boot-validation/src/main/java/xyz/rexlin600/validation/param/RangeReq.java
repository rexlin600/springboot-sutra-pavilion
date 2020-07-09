package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Range 检查
 *
 * @author: hekunlin
 * @since: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangeReq implements Serializable {

    // -----------------------------------------------------------------------------------------------
    // Range
    // -----------------------------------------------------------------------------------------------

    /**
     * 花费
     */
    @Range(min = 1, max = 20, message = "参数错误：花费最多20")
    private BigDecimal spend;

    /**
     * 地址
     */
    @Range(min = 5, max = 10, message = "参数错误：地址长度范围[5,10]")
    private String address;


}