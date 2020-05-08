package xyz.rexlin600.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 发送消息实体类
 *
 * @author: hekunlin
 * @date: 2020/5/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMsg implements Serializable {

    private List<String> list;

    private String content;

}