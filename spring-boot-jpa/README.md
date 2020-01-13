# 简介

集成 `JPA`，提供简单的示例。目前国内可能主流还是 `Mybatis/MybatisPlus`，经过笔者研究发现使用 `JPA` 国外开发人员居多，这个主要和国内开发大环境有关。个人
认为没必要去争论哪一款更好，更多的是关注于业务本身，提升自己 `SQL` 的内功。

## 准备发车

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
    </dependency>
```

* yaml 配置

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
  jpa:
    properties:
      hibernate:
        hbm2ddl:
          auto: update
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
        format_sql: true
    show-sql: true
```

* 实体类注解

```java
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "xyz.rexlin600.helloworld.config.ManulInsertGenerator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

}
```

* 自定义主键生成策略（看自己需要）

> 因为笔者在测试中（新增数据）需要自定义主键策略所以才有了这个东东，打击根据自己情况来决定是否需要

```java
public class ManulInsertGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);

        if (id != null && Integer.valueOf(id.toString()) > 0) {
            return id;
        } else {
            return super.generate(s, obj);
        }
    }
}
```

* 继承 JpaRepository 类

注意，如果你的接口名称符合 `JPA` 的规范（遵循关键字），是可以不用写实现的，如下面的 `findByUserName` 方法就可以不用谢实现。实际上现在很多优秀的数据层
操作框架都提供了这样的特性，如：`MongoDB`

```java
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据名称查找
     *
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);
}
```


