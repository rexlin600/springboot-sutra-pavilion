package xyz.rexlin600.easy.excel.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 简单类
 *
 * @author: hekunlin
 * @date: 2020/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SimpleData implements Serializable {

    /**
     * 字符串
     */
    private String string;

    /**
     * 日期
     */
    private Date date;

    /**
     * 浮点数
     */
    private Double doubleData;

}