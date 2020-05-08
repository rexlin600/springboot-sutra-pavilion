package xyz.rexlin600.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 接收消息实体体
 *
 * @author: hekunlin
 * @date: 2020/5/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveMsg implements Serializable {

    /**
     * 用户名
     */
    private List<String> nameList;

    /**
     * 内容
     */
    private String content;

}