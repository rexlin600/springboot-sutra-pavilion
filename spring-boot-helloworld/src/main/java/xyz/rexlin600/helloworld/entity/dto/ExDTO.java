package xyz.rexlin600.helloworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ExDTO 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExDTO<T> implements Serializable {

    private String msg;

    private Integer code;

    private T data;

    private String url;

}