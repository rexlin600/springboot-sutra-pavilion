# 简介

`spring-boot-admin-discovery` 这个服务和前面讲到的 `spring-boot-admin` 实际上是类似的，不同点在于引入了注册中心 `Eureka`，配置有一点点的变化。


## 开始发车

这里需要区分 `Client` 和 `Server` 端、同时多了一个 `Eureka` 服务（先启动 `Eureka` 服务）。

### Eureka 服务

> Eureka 不作为我们本文档的重点，请自行查看相关依赖、配置

第一步，我们先启动 `Eureka` 服务，保证后续服务能够正常注册。启动 `Eureka` 并登录后如下：

![eureka-server](../images/admin/eureka-server.jpg)

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
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        <version>2.1.4.RELEASE</version>
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
        title: spring-boot-admin-server-discovery
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
# eureka config
eureka:
  instance:
    health-check-url-path: /actuator/health
    metadata-map:
      startup: ${random.int}    #needed to trigger info and endpoint update after restart
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10
  client:
    serviceUrl:
      defaultZone: http://admin:admin@localhost:10014/eureka/
    registry-fetch-interval-seconds: 5
    # 【采坑】下面配置必须配置为如下模式，否者报错：Cannot execute request on any known server，但是对程序无影响，可以正常运行
    register-with-eureka: true
    fetch-registry: false
```

* 配置

> 这里笔者为了方便就直接在启动类上增加了相关配置（注解不是 @SpringApplication 了！），你可以单独拎出来（启动类上要写 @SpringApplication）

```java
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@EnableEurekaClient
public class AdminServerDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerDiscoveryApplication.class, args);
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

访问 `http://localhost:10015/login` 并登陆，如下：

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
   <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        <version>2.1.4.RELEASE</version>
    </dependency>
```

* yaml 配置

> 注意这里有两套用户名/密码，二者的用途是不一样的！

```yaml
# 【采坑】注意 yml 的配置使用注释最好不要在键的上方，尤其是多行配置（单行配置一般不会有问题 ）！因为这样在 IDEA 中启动时应用可能无法正常读取配置
spring:
  security:
    user:
      name: admin
      password: admin
      roles: admin
  boot:
    admin:
      client:
        url: http://localhost:10015
        instance:
          metadata:
            user.name: ${spring.security.user.name} # Submitting the credentials using SBA Client，详情参考 spring-boot-admin 官方文档 5.2 节；保护客户端执行器端点；如果不加下面这个则 SpringBootAdmin 无法读取到相关监控信息，SpringBootAdmin 界面中实例会变为 Down 状态
            user.password: ${spring.security.user.password}
        username: ${spring.security.user.name} # In case you use the Spring Boot Admin Client, it needs the credentials for accessing the server，详情参考 spring-boot-admin 官方文档 5.1 节；如果您使用Spring Boot Admin Client，则它需要访问服务器的凭据；即去掉下面的配置无法在 SpringBootAdmin 中访问到应用，提示：Failed to register application as Application...
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
# eureka config
eureka:
  instance:
    health-check-url-path: /actuator/health
    metadata-map:
      user.name: ${spring.security.user.name} # Submitting the credentials using Eureka，详情参考 spring-boot-admin 官方文档 5.2 节；使用 Eureka 需要做额外配置如下，否者无法在 SpringBootAdmin 上看到其他应用注册上来
      user.password: ${spring.security.user.password}
      startup: ${random.int}    #needed to trigger info and endpoint update after restart
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10
  client:
    serviceUrl:
      defaultZone: http://admin:admin@localhost:10014/eureka/
    registry-fetch-interval-seconds: 5
    register-with-eureka: true # 【采坑】下面配置必须配置为如下模式，否者报错：Cannot execute request on any known server，但是对程序无影响，可以正常运行
    fetch-registry: false
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

* 启动类注解

不要忘了在启动类上增加 `@EnableDiscoveryClient` 注解！

* 启动客户端

启动客户端后，再次访问 `http://localhost:10012/login` 并登陆，如下（可以看到 `Client` 已经注册上来了）：

![admin-server-up](../images/admin/admin-server-up.jpg)

接着，我们通过 `Wallboard` 进入 `Client` 就可以看到相关的监控信息：

![admin-server-monitor](../images/admin/admin-server-monitor.jpg)

