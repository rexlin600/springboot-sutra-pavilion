package xyz.rexlin600.qrcode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 二维码内容对象
 *
 * @author: rexlin600
 * @date: 2020-03-01
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCode implements Serializable {

    /**
     * 二维码内容
     */
    private String content;

    /**
     * 二维码顶部文字
     */
    private String topText;

    /**
     * 二维码中部文字
     */
    private String centerText;

    /**
     * 二维码底部文字
     */
    private String bottomText;

}