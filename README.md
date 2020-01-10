# SpringBoot2.x Example

![Spring Boot 2.0](https://img.shields.io/badge/Spring%20Boot-2.0-brightgreen.svg)
![Mysql 5.7 +](https://img.shields.io/badge/Mysql-5.7+-blue.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.5.0-yellowgreen.svg)
![license](https://img.shields.io/github/license/rexlin600/springboot2-example)
![star](https://img.shields.io/github/stars/rexlin600/springboot2-example?style=social)
![build](https://img.shields.io/travis/rexlin600/springboot2-example/master)
[![codecov](https://codecov.io/gh/rexlin600/springboot2-example/branch/master/graph/badge.svg)](https://codecov.io/gh/rexlin600/springboot2-example)
[![Coverage Status](https://coveralls.io/repos/github/rexlin600/springboot2-example/badge.svg)](https://coveralls.io/github/rexlin600/springboot2-example)

`springboot2-example` 是一个基于 `Spring Boot 2.x` 的综合性 `仓库`。`Spring Boot 2.x` 中的各种示例，以简单、快捷、精炼为目标，虽然定义为 `example`，但是其具体实现上尽可能的能够帮助大家在实际使用中能够只需要进行少量的修改即可投入使用。

最终的目的是帮助大家快速掌握 `Spring Boot 2.x` 的使用以及与其集成的各类中间件的集成使用。


## 介绍

**本项目提供的示例概览：**

> 说明：
> - 模块名格式均为 `spring-boot-xxx` 的形式，下面的模块名称省略前缀 `spring-boot-`，方便大家查看。
> - 模块占用的端口从 `10001` 开始依次递增，部分模块的服务占用多个端口（详情请查看配置文件）。

| 模块名称 | 模块功能描述 | 
| --- | --- |
| [helloworld](https://github.com/rexlin600/springboot2-examplespringboot2-example/tree/master/spring-boot-helloworld) | 国际惯例，`HelloWorld`；使用容器部署：构建war包；自定义属性、随机数、配置绑定等；全局异常处理 |
| [docker](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-docker) | 集成 `docker` 插件；绑定 `maven` 生命周期相关的命令到 `docker` 命令 |
| [runner](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-runner) | 使用 `CommandLineRunner` 以及 `ApplicationRunner` 的演示 |
| [github](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-github) | 使用 `github api v3` 简易示例；推荐个 `iPhone` 的客户端(付费)：`PPHub`  |
| [redis](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-redis) | 集成 `redis` 集群/哨兵；`redisTemplate` 的常用 `API` |
| [elasticsearch](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-elasticsearch) | 集成 `elasticsearch` 的示例；`es` 的 `rest API` |
| [swagger2](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-swagger2) | 集成 `swagger2` 及常用 `swagger` 注解 |
| [mybatisplus](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-mybatisplus) | 集成 `mybatisPlus`；使用其提供的增强 `CRUD`；代码生成器；SQL注入器；性能分析插件；动态数据源；多租户SQL解析器；动态表名SQL解析器 |
| [rabbitmq](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-rabbitmq) | 集成集群 `RabbitMQ`，发送字符串、对象等；常见六种模式：HelloWorld（Simple）、Work（竞争）、FanOut（发布-订阅）、路由模式（完整的匹配，可参考Direct）、Topic（主题模式）、RPC模式（不常用）；死信队列（DLX） |
| [jpa](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-jpa) | 集成 `JPA`；基础 `CURD` 示例；自定义主键策略 |
| [actuator](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-actuator) | 集成 `Actuator`；自定义Endpoint；访问端点鉴权；端点跨域配置；常见断点的作用、见`actuator.http` |
| [admin](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-admin) | 集成 `Admin`；`yml` 中会说么为什么关于 `用户名/密码` 要那样配置，很多人到了 `2.X` 就被这个搞昏了 ；这个版本没有涉及 `Discovery` 的内容；访问服务端点授权；服务各指标监控（details、metrics、env、beans、configuration properties、task、logger、jvm、web、caches等） |
| [admin-discovery](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-admin-discovery) | 集成 `Admin`，功能同上，切换为通过 `注册中心` 去拉取服务实例信息 |
| [java8](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-java8) | 精炼版 `Java 8` 指南：`Default Method`、`Lambda`、`Functional Interfaces(Predicates、Functions、Suppliers、Consumers、Comparators)`、`Optinals`、`Stream(Filter、Sorted、Map、Match、Count、Reduce)`、`DateAPI`、`Parallel Streams`、`Maps` 等等 |
| [scheduler](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-scheduler) | 集成 `SpringBoot` 定时任务，基于 `anntation` 的入门版本 |
| [mail](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-mail) | 集成 `mail` 邮件服务，提供：文本、HTML、附件、内嵌资源、模板邮件的发送与测试 |
| _ | _ |


## Plan

**新计划**

* spring-boot-xml（扩展支持发送xml参数的请求，进行中）
* spring-boot-sharding-jdbc
* spring-boot-sharding-jdbc-masterslave
* spring-boot-mongodb
* spring-boot-shiro
* spring-boot-upload
  * spring-boot-upload
  * spring-boot-feign-upload
  * spring-boot-zuul-upload
* spring-boot-fastDFS
* spring-boot-validation(jsr303 and hibernate-validation)
* spring-boot-flyway
* spring-boot-ldap
* spring-boot-transaction
* spring-boot-async
* spring-boot-logging
* spring-boot-security
* spring-boot-security-cas
* spring-boot-oauth2
* spring-boot-starter
* spring-boot-image
* spring-boot-configuration
* spring-boot-aspect
* spring-boot-retry
* spring-boot-disconf
* spring-boot-kafka
* spring-boot-rocketmq
* spring-boot-mockito
* spring-boot-ignite
* spring-boot-elastic-job
* spring-boot-influxdb
* spring-boot-websocket
* spring-boot-prometheus-grafana
* spring-boot-elk-filebeat
* spring-boot-apm
  * spring-boot-skywalking
  * spring-boot-zipkin
  * spring-boot-pinpoint
* spring-boot-mvc
* spring-boot-dubbo
* spring-boot-benchmark(container、api-gatway、mvcAndWbflux)
* spring-boot-ratelimit
* spring-boot-xxx
 

**后期完善**

1. 完善 `spring-boot-mybatisplus`
2. 完善 `spring-boot-rabbitmq`
3. 完善 `spring-boot-elasticsearch`
4. 完善 `spring-boot-redis`
5. 完善 `spring-boot-admin`（mail）


## 参考

- [参考文档 · 汇总](https://github.com/rexlin600/springboot2-example/blob/master/docs/reference.md)