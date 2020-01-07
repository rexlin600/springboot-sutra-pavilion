package xyz.rexlin600.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用mq类型 enum
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum InvokeTypeEnum {

    DEFAULT("default", 0),
    DIRECT("direct", 1),
    FANOUT("fanout", 2),
    HEADER("header", 3),
    TOPIC("config", 4),
    CUSTOM("custom", 5);


    private String type;
    private Integer code;

}