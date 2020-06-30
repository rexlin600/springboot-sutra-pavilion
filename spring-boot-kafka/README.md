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


## Kafka 安装与部署

略，网上教程比较多


## Kafka Features

- 同时为发布和订阅提供高吞吐量
- 消息持久化
- 分布式（支持 `Server` 间的消息分区及分布式消费，同时保证每个 `partition` 内的消息顺序传输。这样易于向外扩展，所有的 `producer`、`broker` 和 `consumer` 都会有多个，均为分布式的，无需停机即可扩展机器）
- 消费消息采用 `pull` 模式（消息被处理的状态是在 `consumer` 端维护，而不是由 `server` 端维护，`broker` 无状态，`consumer` 自己保存 `offset`）
- 支持 `online` 和 `offline` 的场景（同时支持离线数据处理和实时数据处理）


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

- single node - single broker（单节点、单服务器）
- single node - multiple broker（单节点、多服务器）
- multiple node - multiple broker（多节点、多服务器）

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

即：`Kafka` 的消息组织方式是三级结构：`主题（Topic）` - `分区（Partition）` - `消息（Record）`

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


## Kafka 消息引擎模型

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


## Kafka 分区策略

> 如果要自定义分区策略，你需要显式地配置生产者端的参数partitioner.class。这个参数该怎么设定呢？方法很简单，在编写生产者程序时，你可以编写一个具体的类实现org.apache.kafka.clients.producer.Partitioner接口

- 轮询
- 随机
  ```java
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    return ThreadLocalRandom.current().nextInt(partitions.size());
  ```
- 按消息 `key` 分区
  ```java
    // 注意一下方式并不是安全可靠的，因为还要考虑分区大小变化的情况！
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    return Math.abs(key.hashCode()) % partitions.size();
  ```
- 其他自定义分区策略（按地域、按业务等）

在本项目示例中采用的 `Kafka Client` 的默认分区策略（实现类在 `org.apache.kafka.clients.producer.internals.DefaultPartitioner` 中）为：

* 如果指定了 `partition` 就直接发送到该分区
* 如果没有指定分区但是指定了 `key`，就按照 `key` 的 `hash` 值选择分区
* 如果 `partition` 和 `key` 都没有指定就使用轮询策略。而且如果 `key` 不为 `null`，那么计算得到的分区号会是所有分区中的任意一个
* 如果 `key` 为 `null` 并且有可用分区时，那么计算得到的分区号仅为可用分区中的任意一个


## Kafka 压缩算法

> 吞吐量：LZ4 > Snappy > zstd、GZIP
> 压缩比：zstd > LZ4 > GZIP > Snappy

- GZIP
- LZ4
- Snappy
- zstd（2.1.0+）


## Kafka 消息丢失处理

首先需要明确两个概念：

- `Kafka` 只保证 “已提交” 的消息的持久化
- `Kafka` 对可持久化的消息做 “有限度” 的保证

上面两句话其实不难理解，比如你消息不合法、没有正确传输到 `Broker`，那么 `Kafka` 就不认为你是 “已提交” 的消息；此外 `Kafka` 不保证任何情况下的消息不丢失，比如 `Broker` 宕机了、网络瘫痪了等

> 一般来说消息丢失有两种大场景，一种发生在 Producer、另一种发生在 Consumer；此外实际上还有一种因为主题 Topic 增加导致的消息丢失(如果你配置了auto.offset.reset=latest就会这样)

**针对 Producer 引起的消息丢失：**

- `Producer` 使用带回调的 `API`
- 如果发生错误可以进行重试或进行其他处理

**针对 Consumer 引起的消息丢失：**

- `Consumer` 端维持先消费消息，再更新位移的顺序，这样可以最大限度的保证消息不丢失（带来的问题可能会重复处理消息）
- `Consumer` 从 `Kafka` 读取到消息后开启了多个线程异步处理消息，而 `Consumer` 程序自动向前更新位移，如果某个线程运行失败则相应的消息没有被成功处理，但是位移已经更新了，看上去相当于是消息丢失了（如果是多线程异步处理消费消息，`Consumer` 程序不要开启自动提交位移，而是要应用程序手动提交位移）

**针对 主题 Topic 增加导致的消息丢失：**

- 新建分区丢失是因为没有 `offset` 就从 `lastest` 开始读取，可以改成没有 `offset` 的时候从 `ealiest` 读取应该就可以了

**Kafka 无消息丢失配置 最佳实践**

- 不要使用 `producer.send(msg)`，而要使用 `producer.send(msg, callback)`。记住，一定要使用带有回调通知的 `send` 方法
- 设置 `acks = all`，`acks` 是 `Producer` 的一个参数，代表了你对“已提交”消息的定义。如果设置成 `all`，则表明所有副本 `Broker` 都要接收到消息，该消息才算是“已提交”。这是最高等级的“已提交”定义
- 设置 `retries` 为一个较大的值，这里的 `retries` 同样是 `Producer` 的参数，对应前面提到的 `Producer` 自动重试。当出现网络的瞬时抖动时，消息发送可能会失败，此时配置了 `retries > 0` 的 `Producer` 能够自动重试消息发送，避免消息丢失
- 设置 `unclean.leader.election.enable = false`，这是 `Broker` 端的参数，它控制的是哪些 `Broker` 有资格竞选分区的 `Leader`。如果一个 `Broker` 落后原先的 `Leader` 太多，那么它一旦成为新的 `Leader`，必然会造成消息的丢失。故一般都要将该参数设置成 `false`，即不允许这种情况的发生
- 设置 `replication.factor >= 3`，这也是 `Broker` 端的参数。其实这里想表述的是，最好将消息多保存几份，毕竟目前防止消息丢失的主要机制就是冗余
- 设置 `min.insync.replicas > 1`，这依然是 `Broker` 端参数，控制的是消息至少要被写入到多少个副本才算是“已提交”。设置成大于 `1` 可以提升消息持久性。在实际环境中千万不要使用默认值 `1`
- 确保 `replication.factor > min.insync.replicas`，如果两者相等，那么只要有一个副本挂机，整个分区就无法正常工作了。我们不仅要改善消息的持久性，防止数据丢失，还要在不降低可用性的基础上完成。推荐设置成 `replication.factor = min.insync.replicas + 1`
- 确保消息消费完成再提交，`Consumer` 端有个参数 `enable.auto.commit`，最好把它设置成 `false`，并采用手动提交位移的方式。就像前面说的，这对于单 `Consumer` 多线程处理的场景而言是至关重要的


## Kafka 拦截器

> Kafka 自 0.10.0.0 版本引入拦截器，分为 生产者拦截器（Producer Interceptor） 和 消费者拦截器（Consumer Interceptor）

- 生产者拦截器：在消息发送前以及消息提交成功后植入拦截器逻辑
- 消费者拦截器：消费消息前以及提交位移后编写特定逻辑

同样地，拦截器支持链式规则，你可以编写一组拦截器，`Kafka` 会按照顺序依次执行拦截器逻辑。

如果需要自己实现拦截器则需要实现 `org.apache.kafka.clients.producer.ProducerInterceptor` 接口；如果需要自定义实现消费者拦截器则需要实现 `org.apache.kafka.clients.consumer.ConsumerInterceptor` 接口。

注意：指定拦截器时一定要指定他们的全限定名！

**拦截器的使用逻辑：**

- 客户端监控
- 端到端系统性能检测
- 消息审计


## Kafka 的 Java 生产者是如何管理TCP连接的

- `Kafka` 社区采用 `TCP` 而非 `HTTP` 作为所有请求通信的底层协议；可以利用 `TCP` 本身的一些高级功能如：多路复用请求（严格来讲TCP并不能多路复用，只是提供可靠的消息交付语义保证，如自动重传丢失的报文）、同时轮询多个连接
- `TCP` 连接在创建 `Kafka` 实例时创建；同时如果存在更新元数据后、消息发送后也可能存在 `TCP` 连接创建
- `Producer` 端关闭 `TCP` 连接有两种方式：用户主动关闭、`Kafka` 自动关闭 

注意：`Producer` 通过 `metadata.max.age.ms` 定期更新元数据，在连接多个 `Broker` 的情况下，`Producer` 会从它认为当前负载最少的节点发送请求（负载最小即说明该 Broker 未完成的请求书最少！）


## Kafka 幂等性Producer与事务Producer

> 幂等性：属于数学领域的概念，指的是某些操作或函数能够被执行多次并且其的得到的结果都不会变！

在软件工程领域，对于幂等性的含义稍微有些区别：

- 在命令式编程语言中，若一个子程序是幂等的，那么它不会修改系统状态
- 在函数式编程语言中，很多纯函数天然就是幂等的

在 `Kafka` 中，从 `0.11` 之后实现幂等性 `Producer` 非常简单，只需要设置一个参数（enable.idempotence）为 `true` 即可，但是需要注意的是它只能保证单分区的幂等性，而无法保证其他分区的幂等性！此外，他只能实现单会话上的幂等性，无法实现跨会话的幂等性。

如果要实现多分区以及会话上的消息无重复，就需要使用事务或者依赖事务型 `Producer`，这也是 `Producer` 和事务型 `Producer` 的最大区别！

> 事务：Kafka 的事务概念类似于我们熟知的数据库提供的事务。在数据库领域，事务提供的安全性保障是经典的 ACID，即原子性（Atomicity）、一致性 (Consistency)、隔离性 (Isolation)、持久性（Durability）

在 `Kafka` 中，从 `0.11` 之后也开始提供了对于事务的支持，实现也很简单，满足两个条件即可。和幂等性 `Producer` 一样，设置参数（enable.idempotence）为 `true`，设置 `Producer` 端参数（transactional. id），最好设置为一个有意义的名字。目前主要是在 `read commited` 隔离级别上做事情，他能够保证多条消息原子性的写入到目标分区、也能保证 `Consumer` 只能看到事务提交成功后的消息。此外，代码上也有些调整：
```java
    // 增加了事务调用的API
    producer.initTransactions();
    try {
            producer.beginTransaction();
            producer.send(record1);
            producer.send(record2);
            producer.commitTransaction();
    } catch (KafkaException e) {
            producer.abortTransaction();
    }
```

上面的方法可以保证 `record1`、`record2` 被当做一个事务统一提交到 `Kafka`，即使写入失败（比如事务执行失败），`Kafka` 还是会把它们写入到底层的日志中。因此 `Consumer` 端读取 `Producer` 发送的消息也是需要做一些变更的，只需要设置 `isolation.level` 参数的值即可，目前这个参数有以下两耳光取值：

- `read_uncommitted`：这是默认值，表明 Consumer 能够读取到 Kafka 写入的任何消息，不论事务型 Producer 提交事务还是终止事务，其写入的消息都可以读取。很显然，如果你用了事务型 Producer，那么对应的 Consumer 就不要使用这个值
- `read_committed`：表明 Consumer 只会读取事务型 Producer 成功提交事务写入的消息。当然了，它也能看到非事务型 Producer 写入的所有消息

注意：

- 事务型 `Producer` 显然很相比幂等性 `Producer`、普通 `Producer` 更加耗费性能，因此不要盲目使用！
- 版本 `0.11.0.2`，设置 `transactional.id` 并开启事务后，须同时保证 `retries>1`（未实测）


## Kafka 消费者组与Rebalance

> Consumer Group 是 Kafka 提供的可扩展且㕛容错性的消费者机制；同一个组内可以由多个消费者/消费者实例，它们共享一个 Group ID，组内所有消费者协调在一起来消费订阅主题的所有分区，注意每一个分区只能被同一个分组内的一个 Consumer 实例消费

- Consumer Group 由一个或多个 Consumer 实例组成
- Group ID 是一个字符串，在一个 Kafka 集群中，用来唯一标识一个 Consumer Group
- Consumer Group 下所有实例订阅的主题的单个分区，只能分配给组内的某个 Consumer 实例来消费；当然这个分区也可以被其他的 Group 的实例消费

`Kafka` 仅仅使用 `Consumer Group` 这一种机制，却同时实现了传统消息引擎系统的两大模型：如果所有实例都属于同一个 `Group`，那么它实现的就是消息队列模型；如果所有实例分别属于不同的 `Group`，那么它实现的就是`发布 / 订阅模型`

理想情况下，`Consumer` 实例的数量应该等于该 Group 订阅主题的分区总数；因为如果设置大于总分区数的 `Consumer` 实例只会浪费资源而没有好处

`Consumer Group` 通关管理一组类似 `KV` 对的数据来管理 `Consumer` 消费分区的最新位移（OffSet），其中 `K` 代表分区，`V` 代表 `Consumer` 消费该分区的最新位移

老版本的 `Consumer Group` 把位移保存在 `ZK` 中，但是 `ZK` 并不适合进行频繁的写更新，但是 `Consumer Group` 的位移更新却是一个非常频繁的操作，这样的大吞吐量的写操作会极大地影响 `ZK` 集群的性能，因此 `Kafka` 社区在新版本的 `Kafka` 中重新设计了 `Consumer Group` 的位移管理方式，即将位移通过内部主题 `__consumer_offsets` 来管理

**Rebalance：**

`Rebalance` 本质上是一种协议，规定了一个 `Consumer Group` 下的所有 `Consumer` 如何达成一致，来分配订阅 `Topic` 的每个分区

> 什么叫 Rebalance 呢？

比如某个 `Group` 下有 `20` 个 `Consumer` 实例，它订阅了一个具有 `100` 个分区的 `Topic`。正常情况下，`Kafka` 平均会为每个 `Consumer` 分配 `5` 个分区。这个分配的过程就叫 `Rebalance`。

> 触发 `Rebalance` 的条件：

- 组成员数量发生改变：比如有 `Consumer` 实例加入或离开组
- 订阅主题数发生变更：`Consumer Group` 可以使用正则表达式订阅主题，如果在 `Consumer Group` 运行过程中你信创建了一个满足这个条件的主题则该 `Group` 则会开启 `Rebalance`
- 订阅主题的分区数发生变更：`Kafka` 当前只能允许新增一个主题的分区数，如果分区数增加则会除法订阅该主题的所有 `Group` 发生 `Rebalance`

> Rebalance 的一些痛点

- `Rebalance` 会触发 `stop the world`，会导致整个 `Kafka` 应用线程都会停止工作，也就是说在 `Rebalance` 期间，`Consumer` 什么也干不了
- `Rebalance` 实在是太慢了
- 目前 `Rebalance` 的设计是所有 `Consumer Group` 下的所有实例共同参与，全部重新分配所有分区

> 如何避免 Rebalance？

如果是因为运维层面的增加主题、增加分区这样发生 `Rebalance` 是无法避免的！主要考虑因为组成员数量发生改变导致的 `Rebalance`。

那么对于组成员数量改变，无非就是增加、减少，对于增加自不必说，就是因为业务等原因需要增加（这种一般属于规划内的，是允许的 Rebalance），而对于减少而言，就有很多种可能，首先明确一些关键配置参数：

- `Consumer` 端有个参数，叫 `session.timeout.ms`（Consumer 定期向 Coordinator 发送心跳请求证明其还活着），如果在这个配置时间内 `Coordinator`（见下节） 没有收到来自 `Group` 下某个实例的心跳则会认为实例已经挂了，因此会发生 `Rebalance`
- `Consumer` 还提供了一个允许你控制发送心跳请求频率的参数，就是 `heartbeat.interval.ms`，如果这个值设置的不合理就可能导致消耗额外资源，好处是可以及时通过心跳请求的响应体中知道是否开启 `Rebalance`
- 此外，`Consumer` 还有一个参数 `max.poll.interval.ms`，它限定了 `Consumer` 端应用程序两次调用 `poll` 方法的最大时间间隔，默认 `5` 分钟。表示你的 Consumer 程序如果在 `5` 分钟之内无法消费完 `poll` 方法返回的消息，那么 `Consumer` 会主动发起“离开组”的请求，`Coordinator` 也会开启新一轮 `Rebalance`。

搞清楚上面的参数配置之后，建议使用下面的参数从而避免第一类问题：

```properties
# 要保证 Consumer 实例在被判定为“dead”之前，能够发送至少 3 轮的心跳请求，即session.timeout.ms >= 3 * heartbeat.interval.ms
session.timeout.ms = 6s
heartbeat.interval.ms = 2s
```

第二类问题是因为 Consumer 消费时间过长导致的，也就是说你的 Consumer 里面有一些很重的业务逻辑，因此有必要调整如下参数以避免：

```properties
# 注意：这个值需要比你的下游最大处理时间稍长一点，比如业务处理需要 6分钟，你就设置这个值为 7 分钟
max.poll.interval.ms = 7min
```

还有最后一类情况可能导致 `Rebalance`，那么极有可能是因为 `Consumer` 端的 `GC` 出了问题，可以去排查一下是否因为频发的发生了 `Full GC` 导致了 `Rebalance`

**总结：**

> 防止 Rebalance 主要考虑如下参数配置：

- session.timeout.ms
_ heartbeat.interval.ms
_ max.poll.interval.ms
_ GC 参数





## Kafka Coordinator

> 所有的 Broker 都有各自的 Coordinator 组件；通过两个算法来确定 Coordinate 所在的 Broker

`Coordinator`，即协调者，在 `Kafka` 中对应的术语是 `Coordinator`，它专门为 `Consumer Group` 服务，负责为 `Group` 执行 `Rebalance` 以及提供位移管理和组成员管理等。

实际上 `Consumer` 端应用程序在提交位移时，实际上是向 `Coordinator` 所在的 `Broker` 提交位移，同样地，当 `Consumer` 应用启动时，也是向 `Coordinator` 所在的 `Broker` 发送各种请求，然后由 `Coordinator` 负责执行消费者组的注册、成员管理记录等元数据管理操作。

**确定 Coordinator 所在 Broker 的算法：**

- `第 1 步`：确定由位移主题的哪个分区来保存该 `Group` 数据：`partitionId=Math.abs(groupId.hashCode() % offsetsTopicPartitionCount)`
- `第 2 步`：找出该分区 `Leader` 副本所在的 `Broker`，该 `Broker` 即为对应的 `Coordinator`


## Kafka OffSets

前面提到了老版本的 `Kafka` 中是通过 `ZK` 来管理 `Consumer` 的位移数据的，在 `0.8.2.x` 版本开始社区就开始修改这种设计并且最终在新版本中推出了新的位移主题的管理方式。实际上就是讲 `Consumer` 的位移数据作为一条条普通的 `Kafka` 消息提交到 `__consumer_offsets` 中

**位移主题的设计：**

> 我们可以简单的理解位移的消息格式为一个 `KV` 键值对

- 位移主题的 `Key` 应该保存 `3` 部分内容：`<GroupID, 主题名, 分区号>`
- 位移主题的消息格式有 3 种
    - 保存位移值、其他元数据（时间戳、用户自定义数据），可以粗浅的认为就是保存了位移值的消息
    - 用于保存 `Consumer Group` 信息的消息；用于注册 `Consumer Group` 的消息
    - 用于删除 `Group` 过期位移甚至是删除 `Group` 的消息；墓碑消息（tombstone 消息），也成为了 `delete mark`（它的消息体为 null），一旦某个 `Consumer Group` 下的所有 `Consumer` 实例都停止了，而且他们的位移数据都已经被删除时，`Kafka` 会向位移主题的对应分区写入墓碑消息，表明要彻底删除这个 `Group` 的信息

**Kafka 位移提交方式：**

- 自动提交：推荐使用手动提交位移，自动提交位移会存在问题：只有 `consumer` 一直启动设置，他就会无限期地向主题写入消息。
- 手动提交

**位移主题何时创建？**

`Kafka` 集群中的某一个 `Consumer` 程序启动时，`Kafka` 会自动创建位移主题，并且用户无法修改！！！如果位移主题是 `Kafka` 自动创建的，那么该主题的分区数是 `50`，副本数是 `3`（如果在不清楚各个版本情况下建议还是让 Kafka 自动创建，因为社区反馈了一个 bug 就是因为 Kafka 硬编码了 50 导致的一些问题）

**Kafka 如何保证位移主题不会无限膨胀？**

`Kafka` 使用 `Compact` 策略来删除位移主题中的过期消息，避免该主题无限期膨胀；同时 `Kafka` 提供了专门的后台线程定期地巡检待 `Compact` 的主题，看看是否存在满足条件的可删除数据，这个后台线程叫 `Log Cleaner`，如果你的环境出现过位移主题无线膨胀占用过多磁盘空间的问题，建议查一下这个 `Log Cleaner` 的线程状态。

**Consumer 启动后如何去位移主题拿到该 Consumer 上次消费的最新 offset？**

它会去寻找其 `Coordinator Leader` 副本对应的 `broker` 去拿。根据 `group.id` 找到对应 `Coordinator` 的分区数

**为什么 Kafka 要自己去维护位移主题？**

为什么 `Kafka` 不借助于外部系统去管理、维护 `__consumer_offsets` 呢？原因：既然 `Kafka` 天然实现了高持久性和高吞吐量，那么任何有这两个需求的子服务自然也就不必求助于外部系统，用 `Kafka` 自己实现就好了。



## 参考文章

- [强烈推荐 Kafka 笔记](https://www.kancloud.cn/nicefo71/kafka/1470863)
- [Kafka QuickStart](https://kafka.apache.org/quickstart)
- [Kafka消息单播与多播的具体实现方式](https://baijiahao.baidu.com/s?id=1636294437137747686&wfr=spider&for=pc)
- [kafka 集群安装 部署](https://www.dazhuanlan.com/2019/12/23/5e00667f0d07d/)
- [Kafka auto.offset.reset值详解](https://blog.csdn.net/lishuangzhe7047/article/details/74530417)
- [使用两种多线程模式消费kafka数据](https://zhuanlan.zhihu.com/p/144870495?from_voters_page=true)
- [解决kafka集群由于默认的__consumer_offsets这个topic的默认的副本数为1而存在的单点故障问题](https://www.cnblogs.com/jun1019/p/6634545.html)
- [Kafka设计解析（二）复制](https://www.jianshu.com/p/25c18d7bafcd)
- [Kafka设计解析（三）恰好一次和事务消息](https://www.jianshu.com/p/f77ade3f41fd)
