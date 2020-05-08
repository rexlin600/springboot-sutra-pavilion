# WebSocket

`WebSocket` 是基于 `TCP` 的网络协议，它实现了服务器可以主动发送消息给客户端，当然客户端也可以给服务器发送消息。

`HTTP` 协议缺陷是通讯只能由客户端发起，以前获取消息时，是前端采用轮询方式向后端请求获取数据，这样效率低，且浪费资源，`WebSocket` 由此诞生。

## 引入依赖

```xml
    <!-- version: 2.1.4.RELEASE -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
```

## 乞丐版聊天室功能

1. WebSocket 配置

```java
@Configuration
public class WebSocketConfig {

    /**
     * 如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
```

2. 编写 websocket server

请参考 `WebSocketServer.java`

3. 编写测试 html

请参考 `websocket.html`

4. 测试聊天室

用浏览器打开两个 `websocket.html`，注意不要一样的名称，然后发送消息给对应人即可，消息形式如下：

```json
{
	"nameList": ["Queen"],
	"content": "Hello My Queen, I'm Durant"
}
```

然后另一个人即可在网页上看到消息

当然，我们还可以在服务端的 `OnMessage` 去实现更加复杂的消息处理、转化、推送等