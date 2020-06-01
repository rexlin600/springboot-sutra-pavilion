# 简介

`Redisson` 是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。它不仅提供了一系列的分布式的Java常用对象，
还提供了许多分布式服务。其中包括(`BitSet, Set, Multimap, SortedSet, Map, List, Queue, BlockingQueue, Deque, BlockingDeque,
 Semaphore, Lock, AtomicLong, CountDownLatch, Publish / Subscribe, Bloom filter, Remote service, Spring cache,
  Executor service, Live Object service, Scheduler service`) `Redisson` 提供了使用Redis的最简单和最便捷的方法。
  `Redisson` 的宗旨是促进使用者对 `Redis` 的关注分离（Separation of Concern），从而让使用者能够将精力更集中地放在处理业务逻辑上。

下面是 `redisson` 的架构：

![](https://camo.githubusercontent.com/33f29c6abc2ad60c4f90f1dd08ce19ff17f12688/68747470733a2f2f7265646973736f6e2e6f72672f6172636869746563747572652e706e67)

`Redisson` 底层采用的是 `Netty` 框架。支持 `Redis 2.8` 以上版本，支持 `Java1.6+` 以上版本。


## Redisson 配置方法

`Redisson` 配置总共有八类，如下：

* 程序化配置方法
* 文件方式配置
* 常用设置
* 集群模式
* 云托管模式
* 单Redis节点模式
* 哨兵模式
* 主从模式

具体可参考文章： [配置方法](https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95)

## 与 SpringBoot 集成

这里演示的为 `SpringBoot 2.X` 与 `Redisson` 的集成：

**引入依赖：**

```xml
    <!-- 引入 redisson starter 及 redisson-spring-data-21 集成 -->
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson-spring-boot-starter</artifactId>
        <version>3.11.0</version>
    </dependency>
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson-spring-data-21</artifactId>
        <version>3.11.0</version>
    </dependency>
```

如果你在集成过程中发现缺少某些类，请尝试降级 `redisson-spring-data` ，这个包的模块支持如下：

| redisson-spring-data module name	| Spring Boot version |
| ---- | ---- |
| redisson-spring-data-16  | 1.3.x |
| redisson-spring-data-17	| 1.4.x |
| redisson-spring-data-18	| 1.5.x |
| redisson-spring-data-20	| 2.0.x |
| redisson-spring-data-21	| 2.1.x |
| redisson-spring-data-22	| 2.2.x |


**配置：**

```properties
# redis 公共配置
# common spring boot settings

spring.redis.database=
spring.redis.host=
spring.redis.port=
spring.redis.password=
spring.redis.ssl=
spring.redis.timeout=
spring.redis.cluster.nodes=
spring.redis.sentinel.master=
spring.redis.sentinel.nodes=

# Redisson 配置
# Redisson settings

#path to config - redisson-single.yaml
spring.redis.redisson.config=classpath:redisson.yaml
```


## 程序配置化集成

> 说明，这里演示的为单机redis配置方式，实质上集群、哨兵模式类似。

1. 引入依赖
  ```yaml
  <!-- 引入 redisson starter 及 redisson-spring-data-21 集成 -->
  <dependency>
      <groupId>org.redisson</groupId>
      <artifactId>redisson-spring-boot-starter</artifactId>
      <version>3.11.0</version>
  </dependency>
  <dependency>
      <groupId>org.redisson</groupId>
      <artifactId>redisson-spring-data-21</artifactId>
      <version>3.11.0</version>
  </dependency>
  ```
2. 编写配置，参考 `RedissonConfig.java`
3. 编写 `yml` 文件，如下：
  ```yaml
  spring:
    application:
      name: reddison
    redis:
      database: 0
      host: 127.0.0.1
      port: 6379
      lettuce:
        pool:
          min-idle: 0
          max-active: 8
          max-idle: 8
          max-wait: -1
  server:
    port: 10026
  ``` 

## 外部化配置集成

1. 引入依赖（这里要注释掉 `RedissonConfig.java`）
  ```yaml
  <!-- 引入 redisson starter 及 redisson-spring-data-21 集成 -->
  <dependency>
      <groupId>org.redisson</groupId>
      <artifactId>redisson-spring-boot-starter</artifactId>
      <version>3.11.0</version>
  </dependency>
  <dependency>
      <groupId>org.redisson</groupId>
      <artifactId>redisson-spring-data-21</artifactId>
      <version>3.11.0</version>
  </dependency>
  ```
2. 编写 `yml` 文件，如下：
  ```yaml
  spring:
    application:
      name: reddison
    redis:
      database: 0
      host: 127.0.0.1
      port: 6379
      lettuce:
        pool:
          min-idle: 0
          max-active: 8
          max-idle: 8
          max-wait: -1
    profiles:
      active: single  # redisson 外部配置文件
  server:
    port: 10026
  ```

## 额外：为什么我不写 `spring.redis.redisson.config` 也能集成哨兵模式？

深入 starter 源码，我们可以看到这里有一个这样的判断，这就是为什么你不写 `spring.redis.redisson.config` 这个配置也能集成哨兵模式的原因，
因为它会自动先判断是否存在相应配置，如果没有则会判断你当前的 `redis` 配置是否为哨兵模式，如果是则会为其配置上相应的信息，这样的化也意味着你写的 `redisson.yml` 没有起作用！！！

![](http://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/2020-05-31-130521.png)


## 参考

- [redisson 文档](https://github.com/redisson/redisson/wiki)
- [第三方框架集成](https://github.com/redisson/redisson/wiki/14.-%E7%AC%AC%E4%B8%89%E6%96%B9%E6%A1%86%E6%9E%B6%E6%95%B4%E5%90%88)
