package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * TbFlyway 类
 *
 * @author: rexlin600
 * @date: 2020-01-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbFlyway implements Serializable {

    private Long id;

    private String name;

    private Double length;

    private Date createTime;

}