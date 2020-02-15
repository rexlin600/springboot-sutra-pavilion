# springboot-sutra-pavilion(ssp)

<p align="center">
    <a href="https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html"><img src="https://img.shields.io/badge/Spring%20Boot-2.1.4.Release-brightgreen.svg"></a>
    <a href="MySQL 5.7.+"><img src="https://img.shields.io/badge/Mysql-5.7+-blue.svg"></a>
    <a href="JDK 1.8"><img src="https://img.shields.io/badge/JDK-1.8-brightgreen.svg"></a>
    <a href="Maven"><img src="https://img.shields.io/badge/Maven-3.5.0-yellowgreen.svg"></a>
    <a href="license"><img src="https://img.shields.io/github/license/rexlin600/springboot-sutra-pavilion"></a>
    <a href="https://img.shields.io/github/repo-size/rexlin600/springboot-sutra-pavilion"><img src="https://img.shields.io/github/repo-size/rexlin600/springboot-sutra-pavilion"/></a>
</p>

<p align="center">
    <a href="https://www.codacy.com/manual/rexlin600/springboot-sutra-pavilion?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rexlin600/springboot-sutra-pavilion&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/aadfd5654d204443ac773aa619ce8222"/></a>
    <a href="https://travis-ci.org/rexlin600/springboot-sutra-pavilion.svg?branch=master"><img src="https://travis-ci.org/rexlin600/springboot-sutra-pavilion.svg?branch=master"/></a>
    <a href="https://img.shields.io/github/stars/rexlin600/springboot-sutra-pavilion?style=social"><img src="https://img.shields.io/github/stars/rexlin600/springboot-sutra-pavilion?style=social"></a> 
    <a href="https://img.shields.io/github/forks/rexlin600/springboot-sutra-pavilion?style=social"><img alt="GitHub forks" src="https://img.shields.io/github/forks/rexlin600/springboot-sutra-pavilion?style=social"></a>
    <a href="https://img.shields.io/github/watchers/rexlin600/springboot-sutra-pavilion?style=social"><img alt="GitHub watchers" src="https://img.shields.io/github/watchers/rexlin600/springboot-sutra-pavilion?style=social"></a>
</p>


## 文档

<p align="left">
  <span>中文 | <a href="./README.en.md">English</a></span>
</p>

## 项目简介

`springboot-sutra-pavilion` 是一个基于 `Spring Boot 2.x` 的综合性 `仓库`。`Spring Boot 2.x` 中的各种示例，以简单、快捷、精炼为目标，虽然定义为 `example`，但是其具体实现上尽可能的能够帮助大家在实际使用中能够只需要进行少量的修改即可投入使用。

最终的目的是帮助大家快速掌握 `Spring Boot 2.x` 的使用以及集成的各类中间件的使用


## 功能介绍

**本项目提供的示例概览：**

> 说明：
> * 模块名格式均为 `spring-boot-xxx` 的形式，下面的模块名称省略前缀 `spring-boot-`，方便大家查看
> * 模块占用的端口从 `10001` 开始依次递增，部分模块的服务占用多个端口（详情请查看配置文件）

| 模块名称 | 模块功能描述 | 
| --- | --- |
| [helloworld](https://github.com/rexlin600/springboot-sutra-pavilion/blob/master/spring-boot-helloworld/README.md) | 内嵌容器、构建war包；`Properties`属性绑定、配置自定刷新、全局异常处理 |
| [docker](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-docker/README.md) | 集成 `docker` 插件；绑定 `maven` 生命周期相关的命令到 `docker` 命令 |
| [runner](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-runner/README.md) |使用 `CommandLineRunner` 以及 `ApplicationRunner` 的演示 |
| [github](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-github/README.md) |使用 `github api v3` 简易示例；推荐个 `iPhone` 的客户端(付费)`PPHub` ；安卓推荐(免费) `OpenHub` |
| [redis](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-redis/README.md) |集成 `redis` 集群/哨兵；`redisTemplate` 的常用 `API` |
| [elasticsearch](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-elasticsearch/README.md) |集成 `elasticsearch` 的示例；`es` 的 `rest API` |
| [swagger2](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-swagger2/README.md) |集成 `swagger2` 及常用 `swagger` 注解 |
| [mybatisplus](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-mybatisplus/README.md) |集成 `mybatisPlus`；使用其提供的增强 `CRUD`（包括常用的批量插入、删除、统计、聚合查询、模糊查询等）；高级代码生成器 beta版（可自由添加数据库连接配置、选择连接后查询其所有表、最后选择要生成的表）；SQL注入器；性能分析插件；动态数据源；多租户SQL解析器；动态表名SQL解析器 |
| [rabbitmq](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-rabbitmq/README.md) |集成集群 `RabbitMQ`，发送字符串、对象等；常见六种模式：HelloWorld（Simple）、Work（竞争）、FanOut（发布-订阅）、路由模式（完整的匹配，可参考Direct）、Topic（主题模式）、RPC模式（不常用）；死信队列（DLX） |
| [jpa](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-jpa/README.md) |集成 `JPA`；基础 `CURD` 示例；自定义主键策略 |
| [actuator](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-actuator/README.md) |集成 `Actuator`；自定义Endpoint；访问端点鉴权；端点跨域配置；常见断点的作用、见`actuator.http` |
| [admin](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-admin/README.md) |集成 `Admin`；`yml` 中会说么为什么关于 `用户名/密码` 要那样配置，很多人到了 `2.X` 就被这个搞昏了 ；这个版本没有涉及 `Discovery` 的内容；访问服务端点授权；服务各指标监控（details、metrics、env、beans、configuration properties、task、logger、jvm、web、caches等） |
| [admin-discovery](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-admin-discovery/README.md) |集成 `Admin`，功能同上，切换为通过 `注册中心` 去拉取服务实例信息 |
| [java8](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-java8/README.md) |精炼版 `Java 8` 指南：`Default Method`、`Lambda`、`Functional Interfaces(Predicates、Functions、Suppliers、Consumers、Comparators)`、`Optinals`、`Stream(Filter、Sorted、Map、Match、Count、Reduce)`、`DateAPI`、`Parallel Streams`、`Maps` 等等 |
| [scheduler](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-scheduler/README.md) |集成 `SpringBoot` 定时任务，基于 `anntation` 的入门版本 |
| [mail](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-mail/README.md) |集成 `mail` 邮件服务，提供：文本、HTML、附件、内嵌资源、模板邮件的发送与测试 |
| [jdbc](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-jdbc/README.md) |集成 `jdbcTemplate`、提供简单示例；集成多数据源见参考文章 |
| [mongodb](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-mongodb/README.md) |集成 `MongoDB`、提供 `mongodb` 使用 `MongoRepository` 操作 `mongodb` 的示例、集成 `mongodbplus` 提供更多的配置项 |
| [flyway](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-flyway/README.md) |集成 `Flyway`、提供 `flyway` 的示例，实现数据库版本的管理 |
| [transaction](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-transaction/README.md) |集成事务管理（jdbc/jpa，使用 `@Transactional` 即可进行日常开发 |
| [async](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-async/README.md) |通过 `Async` ，通过 `线程池` 实现异步调用、实现优雅关闭、通过 `Future` 获取异步执行结果；如何定义超时等 |
| [logback](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-logback/README.md) | `logback` 使用示例 |
| [redisson](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-redisson/README.md) | `redis` 官方指定 `Java` 版分布式锁 `redisson` |
| [git4clone](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-git4clone/README.md) | 这个应用利用 `多线程技术` 帮助用户实现快速批量下载 `gitlab` 上的项目，用户只需要进行如下修改即可使用 |
| [aop](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-aop/README.md) | `aop` |
| _ | _ |


## TODO

最新动态请查看 <span><a href="./TODO.md">TODO</a></span> 文件

**后期完善**

1. 完善 `spring-boot-mybatisplus`
2. 完善 `spring-boot-rabbitmq`
3. 完善 `spring-boot-elasticsearch`
4. 完善 `spring-boot-redis`
5. 完善 `spring-boot-admin`（mail）
6. 完善 `java8`(maps)
7. 完善 `spring-boot-jdbc`


## 参考

- [参考文档 · 汇总](https://github.com/rexlin600/springboot-sutra-pavilion/blob/master/docs/reference.md)