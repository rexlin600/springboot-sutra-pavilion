# 简介

集成 `MongoDB`、提供 `mongodb` 使用 `MongoRepository` 操作 `mongodb` 的示例、集成 `mongodbplus` 提供更多的配置项


## 开始发车

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <!-- mongodb 扩展配置，提供更多配置属性：连接池 -->
    <dependency>
        <groupId>com.spring4all</groupId>
        <artifactId>mongodb-plus-spring-boot-starter</artifactId>
        <version>1.0.0.RELEASE</version>
    </dependency>
```

* yaml 配置

```yaml
spring:
  data:
    mongodb:
      database: springboot2-example
      uri: mongodb://localhost/${spring.data.mongodb.database} # 也可以使用如下方式连接，注意不同版本连接方式有些不同
      option:
        always-use-m-beans: false
        connect-timeout: 10000
        heartbeat-connect-timeout: 20000
        heartbeat-frequency: 10000
        heartbeat-socket-timeout: 20000
        local-threshold: 15
        max-connection-idle-time: 0
        max-connection-life-time: 0
        max-connection-per-host: 100
        max-wait-time: 120000
        min-connection-per-host: 0
        min-heartbeat-frequency: 500
        server-selection-timeout: 30000
        socket-keep-alive: false
        socket-timeout: 0
        ssl-enabled: false
        ssl-invalid-host-name-allowed: false
        threads-allowed-to-block-for-connection-multiplier: 5
```

* 继承 MongoRepository

> 之前讲 JPA 已经提过了，我们可以使用满足 MongoDB 规范的语句即可不用自己实现相关方法

```java
public interface UserMongoRepository extends MongoRepository<User, Long> {

    /**
     * 建议使用 MongoRepository<T, TD> 来封装自己的 Mongo 操作类，这样在增加方法时 IDEA 会自动提示有哪些操作可以进行，并且不需要我们去实现！
     * <p>
     * 可以参考 images/mongo 的截图
     */

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    void deleteById(Long id);

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

}
```

* 测试

启动服务，运行 `xyz.rexlin600.mongodb.biz.UserMongoBizTest` 示例即可

## 截图

> 方法自动提示

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/mongo-1.png)

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/mongo-2.png)

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/mongo-3.png)