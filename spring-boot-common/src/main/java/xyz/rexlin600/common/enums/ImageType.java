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

    /**
     * GIF
     */
    GIF(1, "GIF"),
    /**
     * JPG
     */
    JPG(2, "JPG"),
    /**
     * PNG
     */
    PNG(3, "PNG"),
    /**
     * JPEG
     */
    JPEG(4, "JPEG"),
    /**
     * BMP
     */
    BMP(5, "BMP"),
    /**
     * TIFF
     */
    TIFF(6, "TIFF"),
    /**
     * EPS
     */
    EPS(7, "EPS"),
    /**
     * EMF
     */
    EMF(8, "EMF"),
    /**
     * WMF
     */
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