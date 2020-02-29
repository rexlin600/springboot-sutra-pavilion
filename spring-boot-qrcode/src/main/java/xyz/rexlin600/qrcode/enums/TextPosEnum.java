package xyz.rexlin600.qrcode.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 二维码文字位置枚举类
 *
 * @author: rexlin600
 * @date: 2020-02-29
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TextPosEnum {

    TOP_TEXT_POSITION(1, "TOP"),
    CENTER_TEXT_POSITION(2, "CENTER"),
    BOTTOM_TEXT_POSITION(3, "BOTTOM");

    private Integer code;
    private String position;

}