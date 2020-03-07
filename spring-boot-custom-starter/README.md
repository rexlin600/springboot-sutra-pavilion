# 简介

自定义数据源 `starter`

数据源 `SQL` 必须使用 `db\codegen.sql\data_source` 这个 `table`


## 如何自定义

> 核心：通过 starter 可以方便其他模块不用再去进行复杂的配置编写，做到"开箱即用"

1. 引入依赖（`spring-boot-starter`、其他）
2. 编写 `xxxAutoConfiguration`、`xxxProperties`
3. 在 `resources\MEETA-INF` 下添加 `spring.factories`
4. 其他需要简化配置的修改（如注解、配置类等）


## 课后小作业

有时间的同学可以尝试将这里定义的动态数据源和代码生成器对接起来！！！