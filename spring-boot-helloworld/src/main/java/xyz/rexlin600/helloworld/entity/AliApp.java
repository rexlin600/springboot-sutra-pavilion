package xyz.rexlin600.helloworld.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付宝小程序配置类
 *
 * @author: rexlin600
 * @date: 2020-02-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliApp {

    private String appId;

    private String privateKey;

    private String aliPublicKey;

    private String aesKey;

}