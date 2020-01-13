# springboot-sutra-pavilion(ssp)

<p align="center">
    <a href="https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html"><img src="https://img.shields.io/badge/Spring%20Boot-2.1.4.Release-brightgreen.svg"></a>
    <a href="MySQL 5.7.+"><img src="https://img.shields.io/badge/Mysql-5.7+-blue.svg"></a>
    <a href="JDK 1.8"><img src="https://img.shields.io/badge/JDK-1.8-brightgreen.svg"></a>
    <a href="Maven"><img src="https://img.shields.io/badge/Maven-3.5.0-yellowgreen.svg"></a>
    <a href="license"><img src="https://img.shields.io/github/license/rexlin600/springboot2-example"></a>
    <a href="https://img.shields.io/github/repo-size/rexlin600/springboot-sutra-pavilion"><img src="https://img.shields.io/github/repo-size/rexlin600/springboot-sutra-pavilion"/></a>
</p>

<p align="center">
    <a href="https://www.codacy.com/manual/rexlin600/springboot-sutra-pavilion?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=rexlin600/springboot-sutra-pavilion&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/aadfd5654d204443ac773aa619ce8222"/></a>
    <a href="https://travis-ci.org/rexlin600/springboot-sutra-pavilion.svg?branch=master"><img src="https://travis-ci.org/rexlin600/springboot-sutra-pavilion.svg?branch=master"/></a>
</p>

<p align="center">
    <a href="https://img.shields.io/github/stars/rexlin600/springboot-sutra-pavilion?style=social"><img src="https://img.shields.io/github/stars/rexlin600/springboot-sutra-pavilion?style=social"></a> 
    <a href="https://img.shields.io/github/forks/rexlin600/springboot-sutra-pavilion?style=social"><img alt="GitHub forks" src="https://img.shields.io/github/forks/rexlin600/springboot-sutra-pavilion?style=social"></a>
    <a href="https://img.shields.io/github/watchers/rexlin600/springboot-sutra-pavilion?style=social"><img alt="GitHub watchers" src="https://img.shields.io/github/watchers/rexlin600/springboot-sutra-pavilion?style=social"></a>
</p>


## Document

<p align="left">
  <span>English | <a href="./README.md">中文</a></span>
</p>


## Introduction

`springboot-sutra-pavilion` Is a comprehensive `Repository` based on` Spring Boot 2.x`。The various examples in Spring Boot 2.x are aimed at simplicity, fastness, and refinement. Although they are defined as `example`, their specific implementation can help everyone as much as possible in actual use. Modified and put into use.
The ultimate goal is to help everyone quickly grasp the use of Spring Boot 2.x and the integration of various middleware integrated with it.


## Features

**Overview：**

> Description：
> - The module name format is in the form of `spring-boot-xxx`. The following module names are omitted from the prefix` spring-boot-`, for your convenience.
> - The number of ports occupied by the module starts to increase from `10001`, and the services of some modules occupy multiple ports (please see the configuration file for details)

| moduleName | FunctionDescription | 
| --- | --- |
| [helloworld](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-helloworld) | 国际惯例，`HelloWorld`；使用容器部署：构建war包；自定义属性、随机数、配置绑定等；全局异常处理 |
| [docker](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-docker) | 集成 `docker` 插件；绑定 `maven` 生命周期相关的命令到 `docker` 命令 |
| [runner](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-runner) | 使用 `CommandLineRunner` 以及 `ApplicationRunner` 的演示 |
| [github](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-github) | 使用 `github api v3` 简易示例；推荐个 `iPhone` 的客户端(付费)：`PPHub`  |
| [redis](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-redis) | 集成 `redis` 集群/哨兵；`redisTemplate` 的常用 `API` |
| [elasticsearch](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-elasticsearch) | 集成 `elasticsearch` 的示例；`es` 的 `rest API` |
| [swagger2](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-swagger2) | 集成 `swagger2` 及常用 `swagger` 注解 |
| [mybatisplus](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-mybatisplus) | 集成 `mybatisPlus`；使用其提供的增强 `CRUD`；代码生成器；SQL注入器；性能分析插件；动态数据源；多租户SQL解析器；动态表名SQL解析器 |
| [rabbitmq](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-rabbitmq) | 集成集群 `RabbitMQ`，发送字符串、对象等；常见六种模式：HelloWorld（Simple）、Work（竞争）、FanOut（发布-订阅）、路由模式（完整的匹配，可参考Direct）、Topic（主题模式）、RPC模式（不常用）；死信队列（DLX） |
| [jpa](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-jpa) | 集成 `JPA`；基础 `CURD` 示例；自定义主键策略 |
| [actuator](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-actuator) | 集成 `Actuator`；自定义Endpoint；访问端点鉴权；端点跨域配置；常见断点的作用、见`actuator.http` |
| [admin](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-admin) | 集成 `Admin`；`yml` 中会说么为什么关于 `用户名/密码` 要那样配置，很多人到了 `2.X` 就被这个搞昏了 ；这个版本没有涉及 `Discovery` 的内容；访问服务端点授权；服务各指标监控（details、metrics、env、beans、configuration properties、task、logger、jvm、web、caches等） |
| [admin-discovery](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-admin-discovery) | 集成 `Admin`，功能同上，切换为通过 `注册中心` 去拉取服务实例信息 |
| [java8](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-java8) | 精炼版 `Java 8` 指南：`Default Method`、`Lambda`、`Functional Interfaces(Predicates、Functions、Suppliers、Consumers、Comparators)`、`Optinals`、`Stream(Filter、Sorted、Map、Match、Count、Reduce)`、`DateAPI`、`Parallel Streams`、`Maps` 等等 |
| [scheduler](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-scheduler) | 集成 `SpringBoot` 定时任务，基于 `anntation` 的入门版本 |
| [mail](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-mail) | 集成 `mail` 邮件服务，提供：文本、HTML、附件、内嵌资源、模板邮件的发送与测试 |
| [jdbc](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-jdbc) | 集成 `jdbcTemplate`、提供简单示例；集成多数据源见参考文章 |
| [mongodb](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-mongodb) | 集成 `MongoDB`、提供 `mongodb` 使用 `MongoRepository` 操作 `mongodb` 的示例、集成 `mongodbplus` 提供更多的配置项 |
| [flyway](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-flyway) | 集成 `Flyway`、提供 `flyway` 的示例，实现数据库版本的管理 |
| [transaction](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-transaction) | 集成事务管理（jdbc/jpa，使用 `@Transactional` 即可进行日常开发 |
| [async](https://github.com/rexlin600/springboot-sutra-paviliontree/master/spring-boot-async) | 通过 `Async` ，通过 `线程池` 实现异步调用、实现优雅关闭、通过 `Future` 获取异步执行结果；如何定义定义超时等 |
| _ | _ |


## TODO

View the  <span><a href="./TODO.md">TODO</a></span> file

**LateImprovement**

1. Complete `spring-boot-mybatisplus`
2. Complete `spring-boot-rabbitmq`
3. Complete `spring-boot-elasticsearch`
4. Complete `spring-boot-redis`
5. Complete `spring-boot-admin`（mail）
6. Complete `java8`(maps)
7. Complete `spring-boot-jdbc`


## Reference

- [ReferenceDocuments · collect](https://github.com/rexlin600/springboot-sutra-pavilionblob/master/docs/reference.md)
