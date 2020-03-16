# 简介

`dds-spring-boot-starter-autoconfigurer` 是一个实现动态数据源切换的 `starter`，默认依赖如下相关库：

- mybatis-plus
- mysql
- HikariCP
- jdbc

也就是说这个 `starter` 是针对使用 `MySQL` 且数据源连接池为 `HikariCP` 的

## 如何使用

可以参考 `dds-test-starter`，我们在业务方法中传一个 `ds` 参数即可实现切换数据源，目前来说使用场景还很有限，属于定制化的 `starter`。

