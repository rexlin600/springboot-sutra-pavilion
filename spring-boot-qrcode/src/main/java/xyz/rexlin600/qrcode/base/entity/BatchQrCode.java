package xyz.rexlin600.qrcode.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * 批量二维码类
 *
 * @author: hekunlin
 * @since: 2020/3/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchQrCode {

    /**
     * 二维码缓冲图像
     */
    private BufferedImage bufferedImage;

    /**
     * 二维码内容对象
     */
    private QrCode qrCode;

}