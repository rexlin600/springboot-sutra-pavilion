package xyz.rexlin600.qrcode.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 二维码文字位置枚举类
 *
 * @author: rexlin600
 * @since: 2020-02-29
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TextPositionEnum {

    /**
     * 顶部
     */
    TOP(1, "TOP"),
    /**
     * 中心
     */
    CENTER(2, "CENTER"),
    /**
     * 底部
     */
    BOTTOM(3, "BOTTOM");

    /**
     * 位置编码
     */
    private Integer code;
    /**
     * 位置
     * TOP：顶部
     * CENTER：中心
     * BOTTOM：底部
     */
    private String position;

}