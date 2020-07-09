package xyz.rexlin600.rabbitmq.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 调用mq类型 enum
 *
 * @author: hekunlin
 * @since: 2020/1/7
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum InvokeTypeEnum {

    /**
     * 默认
     */
    DEFAULT("default", 0),
    /**
     * 直连
     */
    DIRECT("direct", 1),
    /**
     * 广播
     */
    FANOUT("fanout", 2),
    /**
     * header
     */
    HEADER("header", 3),
    /**
     * 配置
     */
    TOPIC("config", 4),
    /**
     * 简单
     */
    SIMPLE("simple", 5),
    /**
     * 工作模式
     */
    WORK("work", 6),
    /**
     * 死信队列
     */
    DL("dl", 7),
    /**
     * 自定义
     */
    CUSTOM("custom", 8);


    private String type;
    private Integer code;

}