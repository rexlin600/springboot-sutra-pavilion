# 简介

rabbitmq 使用指南入门

## 模式

> 如下图：

![](../images/rabbitmq/rabbitmq-model.png)

1. 基本消息模型：生产者–>队列–>一个消费者
2. work消息模型：生产者–>队列–>多个消费者共同消费
3. 订阅模型-Fanout：广播，将消息交给所有绑定到交换机的队列，每个消费者都可以收到同一条消息
4. 订阅模型-Direct：定向，把消息交给符合指定 rotingKey 的队列（路由模式）
5. 订阅模型-Topic：通配符，把消息交给符合routing pattern（主题模式） 的队列（3、4、5这三种都属于订阅模型，只不过进行路由的方式不同。)


## 开始发车

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
```

* yaml 配置

```yaml
spring:
  rabbitmq:
    # 集群模式地址、用户名、密码；下面可以配置多个host:port
    addresses: localhost:5672
    username: admin
    password: admin
    # 必须配置这个才会确认回调
    publisher-confirm-type: correlated
    # 是否启用发布者确认
    publisher-confirms: true
    # 开启发送失败退回
    publisher-returns: true
    listener:
      # 开启ACK
      direct:
        acknowledge-mode: manual
      simple:
        acknowledge-mode: manual
        retry:
          # 允许消息消费失败的重试
          enabled: true
          # 消息最多消费次数3次
          max-attempts: 3
          # 消息多次消费的间隔1秒
          initial-interval: 1000
        # 设置为false，会丢弃消息或者重新发布到死信队列
        default-requeue-rejected: false
```

* 各模式实现

建议参考代码，每个模块的结构一致，分别为 `config`（存储队列、路由、交换器等配置信息）、`consumer`（消费者）、`provider`（生产者）；相应的代码
可以在 `xyz.rexlin600.rabbitmq.pattern` 目录下查看。

```yaml
├─deadletter  // 死信队列
│  ├─config
│  ├─consumer
│  └─provider
├─direct  // 直连模式
│  ├─config
│  ├─consumer
│  └─provider
├─fanout  // 广播模式
│  ├─config
│  ├─consumer
│  └─provider
├─simple  // 入门：简单模式（完整的 queue、exchange、routingkey 配置）
│  ├─config
│  ├─consumer
│  └─provider
├─topic  // 发布-订阅模式
│  ├─config
│  ├─consumer
│  └─provider
└─work  // 工作模式（竞争）
    ├─config
    ├─consumer
    └─provider
```


## 参考

- [RabbitMQ六种模式与SpringBoot整合](https://www.cnblogs.com/itplay/p/10647335.html)
