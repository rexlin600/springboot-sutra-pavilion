# springboot2-example

![Spring Boot 2.0](https://img.shields.io/badge/Spring%20Boot-2.0-brightgreen.svg)
![Mysql 5.7 +](https://img.shields.io/badge/Mysql-5.7+-blue.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.5.0-yellowgreen.svg)
![license](https://img.shields.io/github/license/rexlin600/springboot2-example)
![star](https://img.shields.io/github/stars/rexlin600/springboot2-example?style=social)

基于 SpringBoot2.x 的Demo示例，SpringBoot 中的各种示例，以简单、快捷、使用为目标。帮助大家快速掌握 Spring Boot 各组件的使用。

## SpringBoot 2.x

**本项目提供的示例概览：**

| 序号 | 模块 | 端口 | 描述 | 
| --- | --- | --- | --- |
| 1 | [spring-boot-helloworld](https://github.com/rexlin600/springboot2-examplespringboot2-example/tree/master/spring-boot-helloworld) | 10001 | 国际惯例，`HelloWorld` |
| 2 | [spring-boot-docker](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-docker) | 10002 | 集成 `docker` 插件；绑定 `maven` 生命周期相关的命令到 `docker` 命令 |
| 3 | [spring-boot-runner](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-runner) | 10003 | 使用 `CommandLineRunner` 以及 `ApplicationRunner` 的演示 |
| 4 | [spring-boot-github](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-github) | 10004 | 使用 `github api v3` 简易示例；推荐个 `iPhone` 的客户端(付费)：`PPHub`  |
| 5 | [spring-boot-redis](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-redis) | 10005 | 集成 `redis` 集群/哨兵；`redisTemplate` 的常用 `API` |
| 6 | [spring-boot-elasticsearch](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-elasticsearch) | 10006 | 集成 `elasticsearch` 的示例；`es` 的 `rest API` |
| 7 | [spring-boot-swagger2](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-swagger2) | 10007 | 集成 `swagger2` 及常用 `swagger` 注解 |
| 8 | [spring-boot-mybatisplus](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-mybatisplus) | 10008 | 集成 `mybatisPlus`；使用其提供的增强 `CRUD`；代码生成器；SQL注入器；性能分析插件；动态数据源；多租户SQL解析器；动态表名SQL解析器 |
| 9 | [spring-boot-rabbitmq](https://github.com/rexlin600/springboot2-example/tree/master/spring-boot-rabbitmq) | 10009 | 集成集群 `RabbitMQ`，发送字符串、对象等；常见六种模式：HelloWorld（Simple）、Work（竞争）、FanOut（发布-订阅）、路由模式（完整的匹配，可参考Direct）、Topic（主题模式）、RPC模式（不常用）；死信队列（DLX） |
| 10 | _ | 10010 |
| 11 | _ | 10011 |
| 12 | _ | 10012 |
| 13 | _ | 10013 |
| 14 | _ | 10014 |


## Plan

**新计划**

* spring-boot-jpa
* spring-boot-sharding-jdbc
* spring-boot-sharding-jdbc-masterslave
* spring-boot-task
* spring-boot-scheduler
* spring-boot-mail
* spring-boot-mongodb
* spring-boot-shiro
* spring-boot-upload
  * spring-boot-upload
  * spring-boot-feign-upload
  * spring-boot-zuul-upload
* spring-boot-fastDFS
* spring-boot-actuator
* spring-boot-admin
* spring-boot-validation(jsr303 and hibernate-validation)
* spring-boot-java8
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


## 参考

- [参考文档](https://github.com/rexlin600/springboot2-example/blob/master/docs/reference.md)