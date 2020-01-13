# 简介

`Flyway` 是一个用于数据库版本控制的插件，详情可以访问 [官网](https://flywaydb.org/) 了解


## 开始发车

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
        <version>5.2.4</version>
    </dependency>
```

* 引入插件

```xml
  <plugin>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>5.2.4</version>
    </plugin>
```

* 新增数据库版本

在 `resources` 目录下新增 `db/migration` 目录，并新增 `V1__Flyway_version.sql` 文件

* yaml 配置

```yaml
spring:
  flyway:
    locations: classpath:/db/migration
  datasource:
    url: jdbc:mysql://localhost:3306/flyway_test?useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      # 自动提交 默认值true
      auto-commit: true
      # 此属性控制客户端（即您）将等待来自池的连接的最大毫秒数。 默认值30000（30秒）
      connection-timeout: 30000
      # 此属性控制允许连接在池中闲置的最长时间。 默认值600000(10分钟)，此设置仅适用于minimumIdle定义为小于maximumPoolSize。
      idle-timeout: 600000
      # 此属性控制池中连接的最大生存期。 1800000(30分钟)
      max-lifetime: 1800000
      # 该属性控制HikariCP尝试在池中维护的最小空闲连接数。不推荐使用这个，默认是和maximum-pool-size相等
      minimum-idle: 100
      # 此属性控制池允许达到的最大大小，包括空闲和正在使用的连接。默认值10
      maximum-pool-size: 100
```

* 服务实现

正常的 `MVC` 开发即可

* 测试

启动服务，访问 `xyz.rexlin600.flyway.service.impl.FlywayServiceImplTest` 示例即可

