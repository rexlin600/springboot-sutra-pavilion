# 简介

`Kafka` 是基于发布与订阅的消息系统。它最初由 `LinkedIn` 公司开发，之后成为 `Apache` 项目的一部分

- 本文采用的是 `Kafka` 的集群配置
- `Kafka Manager`（内网环境，注意替换为自己的地址）：[Kafka Manager](http://devkafka.hikcreate.com/)

## Kafka 安装与部署

略，网上教程比较多


## Features

- 同时为发布和订阅提供高吞吐量
- 消息持久化
- 分布式（支持 `Server` 间的消息分区及分布式消费，同时保证每个 `partition` 内的消息顺序传输。这样易于向外扩展，所有的 `producer`、`broker` 和 `consumer` 都会有多个，均为分布式的，无需停机即可扩展机器）
- 消费消息采用 `pull` 模式（消息被处理的状态是在 `consumer` 端维护，而不是由 `server` 端维护，`broker` 无状态，`consumer` 自己保存 `offset`）
- 支持 `online` 和 `offline` 的场景（同时支持离线数据处理和实时数据处理）


## Kafka 基本概念

![](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593349241192&di=396993dfb4142d932cd72c4c1d821395&imgtype=0&src=http%3A%2F%2Fimgedu.lagou.com%2F1515111-20191017144248547-937799681.jpg)

> 图片来自网络

- `Broker`：集群中的一台或多台服务器的统称
- `Topic`：消息类别（物理上不同 Topic 的消息分开存储；逻辑上一个 `Topic` 的消息虽然保存于一个或多个 `broker` 上，但用户只需指定消息的 `Topic` 即可生产或消费数据而不必关心数据存于何处）
- `Partition`：消息类别（`Topic`）物理上的分组，每个分区（Partition）是一个有序的队列，每条消息都会被分配一个有序的 `id`（offset）
- `Producer`：消息和数据的生产者，往 `Kafka` 发送消息的客户端
- `Consumer`：消息和数据的消费者，从 `Kafka` 拉取消息的客户端
- `Consumer Group`：消费者的分组，是 `Kafka` 用于实现单播、多播的手段（之所以不称之为广播，是因为一条消息只能被Kafka同一个分组下某一个消费者消费，而不是所有消费者都能消费，所以从严格意义上来讲并不能算是广播模式，当然如果希望实现广播模式只要保证每个消费者均属于不同的消费者组）


## Kafka 集群配置

- single node - single broker
- single node - multiple broker
- multiple node - multiple broker

**Kafka 的集群配置：**

```properties
broker.id=1  #当前机器在集群中的唯一标识
port=9093 #当前 kafka 对外提供服务的端口，默认是 9092
host.name=192.168.121.101 #这个参数默认是关闭的，在0.8.1有个bug，DNS解析问题，失败率的问题。
log.dirs=/Users/niwei/Downloads/kafka-example/kafka-logs-1 #消息存放的目录，这个目录可以配置为逗号分割的表达式
zookeeper.connect=192.168.120.101:2181,192.168.120.102:2181,192.168.120.103:2181 #设置 zookeeper 集群的连接端口

num.network.threads=3 #这个是 borker 进行网络处理的线程数
num.io.threads=5 #这个是 borker 进行 IO 处理的线程数
socket.send.buffer.bytes=102400 #发送缓冲区的大小，数据先回存储到缓冲区了到达一定的大小后在发送能提高性能
socket.receive.buffer.bytes=102400 #接收缓冲区的大小，当数据到达一定大小后在序列化到磁盘
socket.request.max.bytes=104857600 #这个参数是向 kafka 请求消息或者向 kafka 发送消息的请求的最大数，这个值不能超过 jvm 的堆栈大小
num.partitions=1 #默认的分区数，一个 topic 默认1个分区数
log.retention.hours=24 #默认消息的最大持久化时间，24小时
message.max.byte=5242880  #消息保存的最大值5M
default.replication.factor=2  #kafka 保存消息的副本数，如果一个副本失效了，另一个还可以继续提供服务
replica.fetch.max.bytes=5242880  #取消息的最大直接数
log.segment.bytes=1073741824 #这个参数是因为 kafka 的消息是以追加的形式落地到文件，当超过这个值的时候，kafka 会新建一个文件
log.retention.check.interval.ms=300000 #每隔 300000 毫秒去检查上面配置的 log 失效时间（log.retention.hours=24 ），到目录查看是否有过期的消息如果有则删除
log.cleaner.enable=false #是否启用 log 压缩，一般不用启用，启用的话可以提高性能
```

如果是多节点多 `broker` 的，所以每个 `broker` 的配置文件 `server.properties` 都要按以上说明修改


## Kafka 高可用配置

- `topic`：replication.factor>=3，即副本数至少是3个2<=min.insync.replicas<=replication.factor
- `broker`：leader 的选举条件 unclean.leader.election.enable=false
- `producer`：request.required.acks=-1，producer.type=sync

**Kafka 高吞吐秘诀：**

- 顺序写入
- 内存映射文件
- 标准化二进制消息格式
- `Zero Copy`


## 参考文章

- [Kafka消息单播与多播的具体实现方式](https://baijiahao.baidu.com/s?id=1636294437137747686&wfr=spider&for=pc)
- [kafka 集群安装 部署](https://www.dazhuanlan.com/2019/12/23/5e00667f0d07d/)
- [Kafka auto.offset.reset值详解](https://blog.csdn.net/lishuangzhe7047/article/details/74530417)
