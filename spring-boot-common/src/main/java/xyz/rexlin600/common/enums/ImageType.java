package xyz.rexlin600.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 图片枚举类型
 *
 * @author: rexlin600
 * @date: 2020/6/6
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ImageType {

    GIF(1, "GIF"),
    JPG(2, "JPG"),
    PNG(3, "PNG"),
    JPEG(4, "JPEG"),
    BMP(5, "BMP"),
    TIFF(6, "TIFF"),
    EPS(7, "EPS"),
    EMF(8, "EMF"),
    WMF(9, "WMF");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 名称
     */
    private String name;

}