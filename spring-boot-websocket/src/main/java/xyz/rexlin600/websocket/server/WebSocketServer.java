package xyz.rexlin600.websocket.server;

import cn.hutool.core.collection.CollectionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.rexlin600.websocket.entity.ReceiveMsg;
import xyz.rexlin600.websocket.entity.SendMsg;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * WebSocket 服务实现类
 *
 * @author: hekunlin
 * @since: 2020/5/8
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{id}/{name}")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicLong onlineCount = new AtomicLong();

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArrayList<WebSocketServer> webSocketServerSet = new CopyOnWriteArrayList<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 用户标记
     * <p>
     * id
     * name
     */
    private String id;
    private String name;

    // -----------------------------------------------------------------------------------------------
    // WebSocket Server Impl：建立连接、断开连接、接收消息、发生错误
    // -----------------------------------------------------------------------------------------------

    /**
     * 建立连接
     *
     * @param session
     * @param id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id, @PathParam(value = "name") String name) {
        this.session = session;
        this.id = id;
        this.name = name;

        // 将当前连接纳入到队列、在线人数 + 1
        webSocketServerSet.add(this);
        onlineCount.incrementAndGet();

        log.info("==>  [OPEN] 用户=[{}] 已上线, 当前在线人数=[{}]", name, onlineCount);
    }

    /**
     * 断开连接
     */
    @OnClose
    public void onClose() {
        // 在线人数 - 1 并从队列中剔除对应连接
        onlineCount.decrementAndGet();
        webSocketServerSet.remove(this);

        log.info("==>  [CLOSE] 用户=[{}] 已下线，当前剩余在线人数=[{}]", name, onlineCount);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("==>  [MESSAGE] 服务端收到来自 用户=[{}]的消息=[{}]", name, message);


        // 数据转换
        ReceiveMsg receiveMsg;
        try {
            receiveMsg = new Gson().fromJson(message, ReceiveMsg.class);
        } catch (JsonSyntaxException ex) {
            log.error("==>  [ERROR] 服务端接收 用户=[{}] 的消息错误，异常原因=[{}]", name, ex.getMessage());
            this.sendMsg(new SendMsg(Arrays.asList(this.id), "发送数据格式必须为正确的JSON形式：{\n" +
                    "\t\"nameList\": [\"xxx\"],\n" +
                    "\t\"content\": \"xxx\"\n" +
                    "}"));
            return;
        }

        // 发送给指定用户的消息Kobe
        List<String> nameList = receiveMsg.getNameList();
        Set<WebSocketServer> set = webSocketServerSet.stream().filter(m -> nameList.contains(m.getName())).collect(Collectors.toSet());
        this.sendMsg(new SendMsg(set.stream().map(WebSocketServer::getId).collect(Collectors.toList()), receiveMsg.getContent()));
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("==>  [ERROR] 发生错误=[{}]，用户=[{}]的连接断开", error, name);
    }

    // -----------------------------------------------------------------------------------------------
    // WebSocket Server Send SendMsg
    // -----------------------------------------------------------------------------------------------

    /**
     * 发送消息给指定用户
     *
     * @param sendMsg
     */
    @SneakyThrows
    public void sendMsg(SendMsg sendMsg) {
        // 如果未指定接收人则不发送消息
        List<String> list = sendMsg.getList();
        if (CollectionUtil.isEmpty(list)) {
            return;
        }

        // 发送消息给指定人
        Set<WebSocketServer> set = webSocketServerSet.stream().filter(m -> list.contains(m.getId())).collect(Collectors.toSet());
        set.stream().forEach(m -> {
            try {
                // 同步消息
                m.getSession().getBasicRemote().sendText(sendMsg.getContent());
                // 异步消息
                //m.getSession().getAsyncRemote().sendText(sendMsg.getContent());
            } catch (Exception ex) {
                log.error("==>  [ERROR] 服务端发送消息给 用户=[{}] 发生异常，异常原因=[{}]", m.getId(), ex.getMessage());
                return;
            }
        });
    }

}