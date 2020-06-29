# 简介

`Kafka` 是基于发布与订阅的消息系统。它最初由 `LinkedIn` 公司开发，之后成为 `Apache` 项目的一部分。

`Apache Kafka` 是消息引擎系统，也是一个分布式流处理平台（Distributed Streaming Platform）


## Kafka 版本

**Kafka 版本：**

> Kafka 的版本指的是不同组织或机构发行的 Kafka 版本，他们提供的功能、能力上有一些的不同

- `Apache Kafka`：`Apache` 顶级项目，迭代速度快、社区版本，提供最最基础的组件；如果需要实现监控可以使用开源的 `Kafka Manager`
- `Confluent Kafka`：分为免费版、企业版，其中免费版和 `Apache Kafka` 类似并额外提供了 `Schema` 注册中心、`REST proxy` 等功能、此外还包含了更多的连接器；而企业版则提供了诸如数据中心备份、集群监控等功能
- `Cloudera/Hortonworks Kafka`：大数据云公司提供的服务，天然集成了 Apache Kafka，通过便捷化的界面操作将 Kafka 的安装、运维、管理、监控全部统一在控制台中

**Kafka 版本总结：**

如果你仅仅需要一个消息引擎系统亦或是简单的流处理应用场景，同时需要对系统有较大把控度，那么我推荐你使用 `Apache Kafka`；如果你需要用到 `Kafka` 的一些高级特性，那么推荐你使用 `Confluent Kafka`；如果你需要快速地搭建消息引擎系统，或者你需要搭建的是多框架构成的数据平台且 `Kafka` 只是其中一个组件，那么我推荐你使用这些大数据云公司提供的 `Kafka`

- 本文采用的是 `Kafka` 的集群配置
- 我才用了 `Kafka Manager`（内网环境，注意替换为自己的地址）来对 `Kafka` 实现监控：[Kafka Manager](http://devkafka.hikcreate.com/)

**Kafka版本演进：**

- `0.7`：基础消息队列功能
-` 0.8`：引入副本机制
- `0.8.2.0`：新版本 `Producer API`（Producer API bug 较多）
- `0.9.0.0`：增加了基础的安全认证、权限功能；同时使用 `Java` 重写了新版本 `Consumer API`、引入了 `Kafka Connect` 组件；`Producer API` 比较稳定了（Consumer API bug 较多）
- `0.10.0.0`：引入了 `Kafka Streams`，此时 `Consumer API` 较稳定了（注意：0.10.2.2 修复了一个可能导致 Producer 性能降低的 Bug，如果使用 0.10 版本建议升级至 0.10.2.2）
- `0.11.0.0`：提供幂等性 `Producer API`、`事务 API`、`Kafka 消息格式` 重构（事务 API 还有 bug），此时使用 `0.11.0.3` 基本上用于`线上环境`没有什么大问题了
- `1.0`与 `2.0`：针对 `Kafka Streams` 的各种改进，如果使用 `Kafka` 建议选择 `2.x` 版本（1.1 引入了 `Failover`）


## Kafka 名词术语

* 消息：`Record`，`Kafka` 是消息引擎嘛，这里的消息就是指 `Kafka` 处理的主要对象
* 主题：`Topic`，主题是承载消息的逻辑容器，在实际使用中多用来区分具体的业务
* 分区：`Partition`，一个有序不变的消息序列。每个主题下可以有多个分区     432
* 消息位移：`Offset`，表示分区中每条消息的位置信息，是一个单调递增且不变的值
* 副本：`Replica`，`Kafka` 中同一条消息能够被拷贝到多个地方以提供数据冗余，这些地方就是所谓的副本。副本还分为领导者副本和追随者副本，各自有不同的角色划分。副本是在分区层级下的，即每个分区可配置多个副本实现高可用
* 生产者：`Producer`，向主题发布新消息的应用程序
* 消费者：`Consumer`，从主题订阅新消息的应用程序
* 消费者位移：`Consumer Offset`，表征消费者消费进度，每个消费者都有自己的消费者位移
* 消费者组：`Consumer Group`，多个消费者实例共同组成的一个组，同时消费多个分区以实现高吞吐
* 重平衡：`Rebalance`，消费者组内某个消费者实例挂掉后，其他消费者实例自动重新分配订阅主题分区的过程。`Rebalance` 是 `Kafka` 消费者端实现高可用的重要手段。


## Kafka 安装与部署

略，网上教程比较多


## Kafka Features

- 同时为发布和订阅提供高吞吐量
- 消息持久化
- 分布式（支持 `Server` 间的消息分区及分布式消费，同时保证每个 `partition` 内的消息顺序传输。这样易于向外扩展，所有的 `producer`、`broker` 和 `consumer` 都会有多个，均为分布式的，无需停机即可扩展机器）
- 消费消息采用 `pull` 模式（消息被处理的状态是在 `


.

consumer` 端维护，而不是由 `server` 端维护，`broker` 无状态，`consumer` 自己保存 `offset`）
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

如果是多节点、多 `broker` 的，那么每个 `broker` 的配置文件 `server.properties` 都要按以上说明进行相应的修改


## Kafka 三层消息架构

参考前面的 `Kafka` 架构图，我们可以得到 `Kafka` 具有如下三层体系：

- 第一层是 `Topic` 层，每个主题可以配置 `M` 个分区，而每个分区又可以配置 `N` 个副本
- 第二层是 `Partition` 层，每个分区的 `N` 个副本中只能有一个充当领导者角色，对外提供服务；其他 `N-1` 个副本是追随者副本，只是提供数据冗余之用
- 第三层是 `Record` 层，分区中包含若干条消息，每条消息的位移从 `0` 开始，依次递增
- 最后，客户端程序只能与分区的领导者副本（Leader Replica）进行交互


## Kafka 高可用、高吞吐

**高可用手段：**

- 集群机制：多 `Broker`。`Kafka` 的服务器端由被称为 `Broker` 的服务进程构成，即一个 `Kafka` 集群由多个 `Broker` 组成，`Broker` 负责接收和处理客户端发送过来的请求，以及对消息进行持久化
- 备份机制：`Replication`，备份的思想很简单，就是把相同的数据拷贝到多台机器上，而这些相同的数据拷贝在 `Kafka` 中被称为副本（Replica），副本的数量是可以配置的，这些副本保存着相同的数据，但却有不同的角色和作用。`Kafka` 定义了两类副本：领导者副本（Leader Replica）和追随者副本（Follower Replica）。前者对外提供服务，这里的对外指的是与客户端程序进行交互；而后者只是被动地追随领导者副本而已，不能与外界进行交互.
- 分区机制：即伸缩性（Scalability），`Kafka` 中的分区机制指的是将每个主题划分成多个分区（Partition），每个分区是一组有序的消息日志

**高可用配置：**

- `topic`：replication.factor>=3，即副本数至少是3个2<=min.insync.replicas<=replication.factor
- `broker`：leader 的选举条件 unclean.leader.election.enable=false
- `producer`：request.required.acks=-1，producer.type=sync

**Kafka 高吞吐秘诀：**

> Kafka 使用消息日志（Log）的方式来保存数据，一个日志就是磁盘上一个只能追加写（Append-only）消息的物理文件

- 顺序写入（避免了随机I/O）
- 内存映射文件
- 标准化二进制消息格式
- `Zero Copy`


## 消息引擎模型

消息设计出来之后还不够，消息引擎系统还要设定具体的传输协议，即我用什么方法把消息传输出去，常见的有如下两种

- 点对点模型
- 发布 / 订阅模型

相比 `Kafka`，`JMS` 是 `Java Message Service`，它也是支持上面这两种消息引擎模型的。严格来说它并非传输协议而仅仅是一组 `API` 罢了


## Kafka 部署规划

| 因素 | 考量点 | 建议   |
| ---- | ---- | ----- |
| OS   | 操作系统 I/O 模型 | 部署在 Linux 系统上 |
| 磁盘 | 磁盘 I/O 性能 | 普通环境使用机械磁盘、不需要搭建 RAID 磁盘阵列 |
| 磁盘容量 | 根据消息数、留存时间预估 | 实际使用中建议预留 20~30% |
| 带宽 | 根据实际带宽资源和业务 SLA 预估服务器数量 | 对于千兆网络，建议每台服务器按照 700Mbps 来计算，避免大流量下的丢包 |

**参考问题：**

- 每天需要向 `Kafka` 集群发送 1 亿条消息，每条消息保存两份以防止数据丢失，另外消息默认保存两周时间。现在假设消息的平均大小是 1KB，如何规划 `Kafka` 所需磁盘空间，需要考虑哪些点？（答案：2.25TB）
- 带宽 1Gbps、1 小时处理 1TB 数据，消息需要复制两份，需要多少台 `Kafka` 服务器？（答案：30台，注意 Mbps 与 MB 的换算问题）


## 


## 参考文章

- [Kafka QuickStart](https://kafka.apache.org/quickstart)
- [Kafka消息单播与多播的具体实现方式](https://baijiahao.baidu.com/s?id=1636294437137747686&wfr=spider&for=pc)
- [kafka 集群安装 部署](https://www.dazhuanlan.com/2019/12/23/5e00667f0d07d/)
- [Kafka auto.offset.reset值详解](https://blog.csdn.net/lishuangzhe7047/article/details/74530417)
- [使用两种多线程模式消费kafka数据](https://zhuanlan.zhihu.com/p/144870495?from_voters_page=true)

