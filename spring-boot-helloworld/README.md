# 简介

`spring-boot-helloworld` 是一个基础版的 `springboot` 项目，提供了如下特性：

- 内嵌容器部署
- 构建 `war` 包在 `Tomcat` 运行
- 提供了 `SpringBoot` 的 `Properties` 属性，包括字符串、数值数据、集合（`List`、`Map`）、随机数等的配置绑定
- 配置动态更新
- 全局异常处理，即：`Global Exception Handler`


## 内嵌容器

众所周知，`SpringBoot` 提供了开箱即用的特性，我们再也不用像以前一样把我们的应用程序打包为 `war`，然后单独拎到诸如 `Tomcat` 等容器中运行啦。
因为现在 `SpringBoot` 提供了内嵌容器的支持（`Tomcat`、`Jetty`、`Undertow`），这里以 `Undertow` 为例：

* 引入依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- 默认为tomcat容器，我这里因为是使用 undertow 容器所以排除了 tomcat -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-undertow</artifactId>
    </dependency>
```

* 启动服务

```log
...
2020-01-13 10:35:26.719  INFO 17180 --- [           main] o.s.b.w.e.u.UndertowServletWebServer     : Undertow started on port(s) 10001 (http) with context path ''
2020-01-13 10:35:26.722  INFO 17180 --- [           main] x.r.helloworld.HelloWorldApplication     : Started HelloWorldApplication in 1.503 seconds (JVM running for 4.162)
```

## 构建 WAR 包

> 构建 war 包需要排除内嵌容器的依赖。本例中为 undertow，直接在 pom.xml 中注释掉即可。

* 引入依赖

```xml
    <!-- war 包部署：war包部署时打开，同时注释掉上方 undertow 的依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <!--打包的时候可以不用包进去，别的设施会提供。事实上该依赖理论上可以参与编译，测试，运行等周期。
            相当于compile，但是打包阶段做了exclude操作-->
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
```

* 修改 `pom.xml` 打包为 `war`

```bash
    <packaging>war</packaging>
```

* 增加 `plugin`

```xml
   <!-- war包插件 -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.2</version>
    </plugin>
```

* 使用命令构建

```bash
# 可以使用命令构建 war 包，也就点击使用 IDEA 的 war 插件生成
$ mvn clean package
...[INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time: 1.334 s
   [INFO] Finished at: 2020-01-13T10:45:01+08:00
   [INFO] Final Memory: 22M/276M
   [INFO] ------------------------------------------------------------------------

```

最后就可以将构建的 `war` 包放入到 `Tomcat` 容器中去运行啦。

__值得注意的是使用 `war` 包构建的项目不会使用 `yml` 中的端口配置信息而是使用
如 `Tomcat` 容器的端口，详情可以见 `docs` 目录的参考文章！__


## 配置绑定

进行配置绑定可以很方便的对我们的配置进行管理，首先需要在 `yaml` 或 `properties` 中增加配置：

```yaml
# 各种配置读取
rexlin600:
  simple:
    properties:
      name: "rexlin600"
      web-url: "http://www.rexlin600.com"
      random-str: ${random.value}
      random-int: ${random.int}
      random-long: ${random.long}
      random-int-limit: ${random.int[1,9]}
      random-long-limit: ${random.int[10000,999999]}
  list:
    list[0]: Hello
    list[1]: World
    integer-list: ${random.int},${random.int}
    double-list:
      - 20.11
      - 100.22
  kv:
    map:
      name: rexlin600
      gender: man
      goods: cool
      key1: javaer
      '[user.likes]': beautiful world # 如果Map类型的key包含非字母数字和-的字符，需要用[]括起来
```

* 简单属性的读取

```java
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.simple.properties")
public class SimpleProperties {

    /**
     * 用户名
     */
    private String name;

    /**
     * 个人站点 URI
     */
    private String webUrl;

    /**
     * 随机字符串
     */
    private String randomStr;

    /**
     * 随机int
     */
    private String randomInt;

    /**
     * 随机Long
     */
    private String randomLong;

    /**
     * 随机int，在 1-9 之间
     */
    private String randomIntLimit;

    /**
     * 随机Long，在 10000-999999 之间
     */
    private String randomLongLimit;

}
```

* 集合属性的读取：`List`

```java
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.list")
public class ListProperties {

    private List<String> list;

    private List<Integer> integerList;

    private List<Double> doubleList;

}
```

* 集合属性的读取：`Map`

```java
@Data
@ToString
@Component
@ConfigurationProperties("rexlin600.kv")
public class MapProperties {

    private Map<String, Object> map;

}
```

## 高级：配置自动更新

> 这里会涉及一些微服务的知识，大家不必过于深入，只要知道有这个就行了。相信在以后的学习中，尤其是接触到配置中心时就明白了。

* 添加依赖

```xml
    <!-- 实现配置自动刷新 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
```

* 增加 `@RefreshScope` 注解

```java
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.simple.properties")
@RefreshScope
public class SimpleProperties {

    // ...

}
```

* 验证配置自动刷新

我们启动程序后，调用 `ListPropertiesTest` 测试，第一次发现输出的内容为：

```log
...
2020-01-13 10:57:47.698  INFO 2852 --- [           main] x.r.h.config.SimplePropertiesTest        : ==>  read simple properteis: [SimpleProperties(name=rexlin600, webUrl=http://www.rexlin600.com, randomStr=922834bc2139c140cd3b6566eb4ddbd7, randomInt=305425549, randomLong=4086492910561918095, randomIntLimit=7, randomLongLimit=278728)]
```

我们这时修改 `yaml` 配置文件，不重启服务！再运行测试用例：

```log
...
2020-01-13 11:02:17.294  INFO 3188 --- [           main] x.r.h.config.SimplePropertiesTest        : ==>  read simple properteis: [SimpleProperties(name=rexlin600-refresh, webUrl=http://www.rexlin600.com, randomStr=d88f4d6b616aaf433b3a02b1a44d870f, randomInt=1406206648, randomLong=3572292398210457245, randomIntLimit=8, randomLongLimit=174792)]
```

可以看到 `name` 更新了，是不是神奇！


## 全局异常处理

全局异常处理使用相对比较简单，因为我这里需要让 `rest` 接口访问返回到某个 `html` 页面，所以需要使用到模板引擎的依赖（实际工作中可能不太需要），如下：

* 添加依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
```

* 编写全局异常处理器

```java
@ControllerAdvice(basePackages = {"xyz.rexlin600"})
public class GlobalException {

    /**
     * 默认的异常处理器，返回错误页面
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExHander(HttpServletRequest request, Exception ex) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    /**
     * 自定义异常处理器，直接返回 ExDTO，注意需要加上 @ResponseBody
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ExDTO<String> jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
        ExDTO<String> r = new ExDTO<>();
        r.setMsg(e.getMessage());
        r.setCode(1005);
        r.setData("Hello,I'm your BaseException");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
```

* 编写 `rest` 接口

```java
@Controller
public class ExRest {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("url", "http://www.rexlin600.com");
        return "index";
    }

    @RequestMapping("/default")
    public String defaultEx() throws Exception {
        throw new RuntimeException("默认全局异常");
    }

    @RequestMapping("/base")
    public String myEx() throws BaseException {
        throw new BaseException("自定义异常");
    }

}
```

* 编写模板文件

见 `resources/templates` 下的 `html` 文件

* 测试

访问 `http://localhost:10001/default`、`http://localhost:10001/base` 可以看到全局异常处理器与自定义异常的返回结果

> 默认全局异常

```html
Error Handler
http://localhost:10001/default
默认全局异常
```

> 全局异常：自定义异常

```html
// 20200113110940
// http://localhost:10001/base

{
  "msg": "自定义异常",
  "code": 1005,
  "data": "Hello,I'm your BaseException",
  "url": "http://localhost:10001/base"
}
```

