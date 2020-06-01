package xyz.rexlin600.validation.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 新增 Goods Inner 请求类
 *
 * @author: hekunlin
 * @date: 2020/6/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGoodsInnerReq implements Serializable {

    /**
     * 产地
     */
    @NotBlank(message = "参数错误：产地不可为空")
    private String origin;

    /**
     * 地区ID
     */
    @NotNull(message = "参数错误：地区ID不可为空")
    private Long regionId;

}