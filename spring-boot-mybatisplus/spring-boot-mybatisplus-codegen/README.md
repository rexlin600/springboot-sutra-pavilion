# 简介

`SpringBoot` 集成 `mybatisplus`，包含如下功能：

- 增强 CRUD（包括常用的批量插入、删除、统计、聚合查询、模糊查询等）
- 代码生成器
- SQL注入器
- 性能分析插件
- 动态数据源
- 多租户SQL解析器
- 动态表名SQL解析器

## 注意

特别注意：**`spring-boot-mybatisplus` 下的所有服务均占用 `10008` 端口！！！**


## 开始发车

* 引入依赖

```xml
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.0</version>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-generator</artifactId>
        <version>3.3.0</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
    </dependency>
    <!-- 模板引擎 -->
    <dependency>
        <groupId>org.apache.velocity</groupId>
        <artifactId>velocity-engine-core</artifactId>
        <version>2.1</version>
    </dependency>
    <!-- hutool -->
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.0.6</version>
    </dependency>
```

* 增加配置

```yaml
spring:
  application:
    name: codegen
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/codegen?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    username: root
    password: 123456
    max-idle: 10
    max-active: 15
    max-lifetime: 86430000
    log-abandoned: true
    remove-abandoned: true
    remove-abandoned-time: 60
    sqlScriptEncoding: UTF-8
server:
  port: 10008
# mybatis-plus config
mybatis-plus:
  mapper-locations: classpath:mapper/*.xml
```

* 重点

大家可以重点关注如下内容：

- `templates` 包下的模板文件
- `util` 包下的工具类
- `CodeServiceImpl` 实现类

* 测试

> 数据库增加 `datasource` 记录；修改测试用例中的数据。然后运行测试用例中的测试即可

本地启动服务，参考 `postman` 包下的 `代码生成器.postman_collection.json` 进行测试即可


## SQL注入器

TODO   
      
## 性能分析插件

TODO

## 动态数据源

TODO

## 多租户SQL解析器

TODO

## 动态表名SQL解析器

TODO
