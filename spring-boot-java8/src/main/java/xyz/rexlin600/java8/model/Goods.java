package xyz.rexlin600.swagger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Goods 实体类
 *
 * @author: rexlin600
 * @date: 2020-01-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {

    private Long id;
    private String name;
    private Integer sort;
    private String position;
    private Double weight;
    private String color;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;
}