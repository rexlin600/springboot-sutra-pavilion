# 简介

`SpringBoot` 集成 `mybatisplus`，包含如下功能：

- 增强 CRUD（包括常用的批量插入、删除、统计、聚合查询、模糊查询等）
- 代码生成器
- SQL注入器
- 性能分析插件
- 动态数据源
- 多租户SQL解析器
- 动态表名SQL解析器

## 准备

* 引入依赖

```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
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
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.1.10</version>
        <scope>compile</scope>
    </dependency>
```

* 增加配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring-boot-mybatisplus?useUnicode=true&characterEncoding=utf-8
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
mybatis-plus:
  type-aliases-package: xyz.rexlin600.swagger.model
  mapper-locations: classpath*:/mapper/**/*.xml
```

* 配置

```java
@Configuration
@MapperScan("xyz.rexlin600.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
```

实际上到这里，`SpringBoot` 集成 `MybatisPlus` 就已经结束了，接下来就是常规的 `MVC` 模式的开发了。下面将着重讲解 `MybatisPlus` 提供的一些
额外的功能的集成及使用。


## 代码生成器

TODO

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
