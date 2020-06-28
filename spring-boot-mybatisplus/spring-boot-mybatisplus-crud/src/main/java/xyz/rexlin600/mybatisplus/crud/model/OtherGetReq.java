package xyz.rexlin600.mybatisplus.crud.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * OtherReq
 *
 * @author: hekunlin
 * @date: 2020/6/28
 */
@ToString
@Data
public class OtherGetReq implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

}