package xyz.rexlin600.rabbitmq.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Message 实体类
 *
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Message implements Serializable {

    private Long id;

    private String content;

}