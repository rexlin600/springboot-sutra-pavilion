# 简介

`SpringBoot Admin` 是一款用于管理和监控 `SpringBoot` 应用程序.应用程序作为 `Spring Boot Admin Client` 向为 `Spring Boot Admin Server` 注册（通过HTTP）
或使用 `SpringCloud` 注册中心（例如 `Eureka`、`Consul`）发现 `Client`

本示例采用通过 `HTTP` 方式注册，如果你需要了解通过注册中心（`Eureka`）的方式请参考 [spring-boot-admin-discovery](https://github.com/rexlin600/springboot-sutra-pavilion/tree/master/spring-boot-admin-discovery) 项目


## 开始发车

这里需要区分 `Client` 和 `Server` 端。

### Server 端

* 引入依赖

> 引入 `spring-boot-starter-security` 保证 `Spring Boot Admin Server` 安全

```xml
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.1.4</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

* yaml 配置

```yaml
spring:
  # endpoint & admin security config
  security:
    user:
      roles: admin
      name: admin
      password: admin
  boot:
    admin:
      ui:
        title: spring-boot-admin-server
      notify:
        mail:
          enabled: false
      monitor:
        read-timeout: 10000
# ednpoint config
management:
  endpoints:
    web:
      exposure:
        include: "*"
      cors:
        allowed-origins: "*"
        allowed-methods: "*"
      base-path: /actuator
  endpoint:
    health:
      show-details: always
```

* 配置

> 这里笔者为了方便就直接在启动类上增加了相关配置（注解不是 @SpringApplication 了！），你可以单独拎出来（启动类上要写 @SpringApplication）

```java
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

    @SuppressWarnings("Duplicates")
    @Configuration
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            successHandler.setDefaultTargetUrl(adminContextPath + "/");

            http.authorizeRequests()
                    .antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                    .logout().logoutUrl(adminContextPath + "/logout").and()
                    .httpBasic().and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringAntMatchers(
                            adminContextPath + "/instances",
                            adminContextPath + "/actuator/**"
                    );
            // @formatter:on
        }
    }

}
```

* 启动服务端

访问 `http://localhost:10012/login` 并登陆，如下：

![admin-server-login](../images/admin/admin-server-login.jpg)
![admin-server](../images/admin/admin-server.jpg)

至此，服务端完成，接下来我们去完成 `client` 端。


### Client 端

* 引入依赖

> 引入 `spring-boot-starter-security` 保证 `Spring Boot Admin Server` 安全，并保证能和 `Server` 端通信

```xml
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.1.4</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

* yaml 配置

> 注意这里有两套用户名/密码，二者的用途是不一样的！

```yaml
spring:
  security:
    user:
      name: admin
      password: admin
      roles: admin
  boot:
    admin:
      client:
        url: http://localhost:10012
        instance:
          metadata:
            # Submitting the credentials using SBA Client，详情参考 spring-boot-admin 官方文档 5.2 节
            # 保护客户端执行器端点；如果不加下面这个则 SpringBootAdmin 无法读取到相关监控信息，SpringBootAdmin 界面中实例会变为 Down 状态
            user.name: ${spring.security.user.name}
            user.password: ${spring.security.user.password}
        # In case you use the Spring Boot Admin Client, it needs the credentials for accessing the server，详情参考 spring-boot-admin 官方文档 5.1 节
        # 如果您使用Spring Boot Admin Client，则它需要访问服务器的凭据；即去掉下面的配置无法在 SpringBootAdmin 中访问到应用，提示：Failed to register application as Application...
        username: ${spring.security.user.name}
        password: ${spring.security.user.password}
# management endpoint
management:
  endpoints:
    web:
      exposure:
        include: "*"
      cors:
        allowed-origins: "*"
        allowed-methods: "*"
  endpoint:
    health:
      show-details: always
```

* 配置

```java
@Configuration
public class SecurityPermitConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.roles}")
    private String roles;

    /**
     * configure
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf 忽略 actuator 的请求
        http.csrf().ignoringAntMatchers("/actuator/**");

        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                // 具有 role 的角色才允许访问，保护端点
                .anyRequest().hasRole(roles)
                .and()
                .httpBasic();
    }

}
```

* 启动客户端

启动客户端后，再次访问 `http://localhost:10012/login` 并登陆，如下（可以看到 `Client` 已经注册上来了）：

![admin-server-up](../images/admin/admin-server-up.jpg)

接着，我们通过 `Wallboard` 进入 `Client` 就可以看到相关的监控信息：

![admin-server-monitor](../images/admin/admin-server-monitor.jpg)

