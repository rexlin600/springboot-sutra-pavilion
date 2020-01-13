# 简介

整合 `spring-boot-starter-data-redis`，并排除 `lettuce` 包，使用 `jedis` 代替（方便连接集群环境）。本服务提供 `redis` 的常见数据结构的
使用方式，包括：

- string
- list
- set
- zset(SortedSort)
- hash
- geo
- hyperloglog

## 依赖

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <!-- 排除lettuce包，使用jedis代替-->
        <exclusions>
            <exclusion>
                <groupId>io.lettuce</groupId>
                <artifactId>lettuce-core</artifactId>
            </exclusion>
        </exclusions>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.1.10</version>
        <scope>compile</scope>
    </dependency>
```

* 修改配置

> 注意，我这里是集群模式。需要修改为单个 redis 的方式请注意修改（比较简单，这里就不赘述了）

```yaml
spring:
  # redis连接哨兵/集群模式参考文章 https://blog.csdn.net/qq_39669058/article/details/89158145
  redis:
    sentinel:
      nodes[1]: 10.197.236.169:26379
      nodes[0]: 10.197.236.154:26379
      nodes[2]: 10.197.236.184:26379
      master: mymaster
    password: 123456
    database: 10
    jedis:
      pool:
        max-idle: 100
        min-idle: 8
        test-on-borrow: true
        max-redirects: 3
        max-active: 200
        max-wait: 5000ms
        test-on-return: false
        block-when-exhausted: true
    timeout: 5000ms
```

## redis 常见数据结构与使用场景介绍

* `String`

| Title | Desc |
| --- | --- |
| 介绍 | 纯字符串作为 `value` 形式存储、可以存储 `json`格式、数值型等 |
| 适用场景 | 存储简单的 `key-value` 结构数据。 比如：用户信息、登录信息（token）；此外还有 `INCR`、`DECR` 等用法 |
| 注意 | 一般单个 `String` 不要存储超过 `1MB` 的数据 |

* `Hash`

| Title | Desc |
| --- | --- |
| 介绍 | 存储具有 `两层 key` 的数据结构 |
| 适用场景 | 存储商品数据、点赞、收藏等 |
| 注意 | `Hash` 中的第二层 `Key` 不要超过 `200` 个最好 |

* `List`

| Title | Desc |
| --- | --- |
| 介绍 | 有序的、可重复的集合，可以使用诸如：左推、左拉、右推、右拉等方式来存储数据 |
| 适用场景 | 队列、发布-订阅模式 |
| 注意 | `redis` 的队列是一种轻量级别的，没有队列重试、队列重放机制。消费完队列消息在 `redis` 代表已经删除了 |

* `Set`

| Title | Desc |
| --- | --- |
| 介绍 | 无序的，不能重复的集合。并且在redis中，只有一个key |
| 适用场景 |  保存标签、不重复的数据等 |
| 注意 | 一般单个 `Set` 不要存储超过 `1MB` 的数据 |

* `SortedSet`

| Title | Desc |
| --- | --- |
| 介绍 | 有序的，并且不能重复；既有 `list` 的有序，又有 `set` 的不可重复性 |
| 适用场景 |  排行榜 |
| 注意 | 一般单个 `SortedSet` 不要存储超过 `1MB` 的数据 |

* `hyperloglog`

| Title | Desc |
| --- | --- |
| 介绍 | `hyperloglog` 从创建一开始存储12KB的容量 |
| 适用场景 |  统计、例如统计 `PV`、`UV` 等数据 |
| 注意 | - |

* `geo`

| Title | Desc |
| --- | --- |
| 介绍 | `GEO` 可以保存地理位置的信息，并且可以计算地理位置的距离等 |
| 适用场景 | 使用地理位置时，并且需要计算，快速的场景 |
| 注意 | - |


## 使用

使用示例请参考代码部分
