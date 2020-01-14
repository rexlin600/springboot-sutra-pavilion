# 简介

集成 `jdbcTemplate` 提供简单示例；集成多数据源见参考文章


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
```

* yaml 配置

> 我这里开启了打印 SQL

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring-boot-mybatisplus?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
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
server:
  port: 10020
logging:
  level:
    java.sql: debug
```

* 服务实现

```java
@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据id查找用户
     *
     * @param id
     */
    @Override
    public User selectById(Long id) {
        String sql = "select id,name,age,email from user where id=?";

        User user = jdbcTemplate.query(sql, new Object[]{id}, (ResultSetExtractor<User>) resultSet -> {
            if (resultSet.next()) {
                return User.builder()
                        .id(Long.valueOf(resultSet.getString("id")))
                        .name(resultSet.getString("name"))
                        .age(Integer.valueOf(resultSet.getString("age")))
                        .email(resultSet.getString("email"))
                        .build();
            } else {
                return null;
            }
        });

        return user;
    }

    /**
     * 根据id删除一个用户
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        String sql = "delete from user where id = " + id;
        jdbcTemplate.execute(sql);
    }

    /**
     * 获取用户总数
     *
     * @return
     */
    @Override
    public Long getAllUsers() {
        String sql = "select count(id) from user";
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        return result;
    }
}
```

* 测试

运行 `xyz.rexlin600.jdbc.service.impl.UserServiceImplTest` 的示例即可

