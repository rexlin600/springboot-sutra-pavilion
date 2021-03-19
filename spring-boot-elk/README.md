# 说明

- 我们使用 `SpringBoot` 项目集成 `logback` 打印日志，同时增加一个 `appender` 将日志发送到消息队列中；
- 然后利用 `logtash` 采集日志到 `ES` 集群；
- 最后通过 `kibanna` 展示日志。

这里的示例项目中我们使用定时任务来模拟产生各个级别的日志以及报错。

## 开始发车

* 引入依赖

```xml
    <!-- amqp -->
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-amqp</artifactId>
</dependency>
        <!-- rabbitmq -->
<dependency>
<groupId>org.springframework.amqp</groupId>
<artifactId>spring-rabbit</artifactId>
</dependency>
        <!-- logback -->
<dependency>
<groupId>net.logstash.logback</groupId>
<artifactId>logstash-logback-encoder</artifactId>
<version>${logstash.logback.encoder.versin}</version>
</dependency>
```

* 启动类

```java

@SpringBootApplication
@EnableScheduling
public class ElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElkApplication.class, args);
    }

}
```

* 实现定时任务

```java

@Component
@Slf4j
public class TaskService {

    /**
     * 生成DEBUG级别日志
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void generateDebugLog() {
        log.debug("==>  自动产生DEBUG级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
    }

    /**
     * 生成INFO级别日志
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void generateInfoLog() {
        log.info("==>  自动产生INFO级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
    }

    /**
     * 生成WARN级别日志
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void generateWarnLog() {
        log.warn("==>  自动产生WARN级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
    }

    /**
     * 生成ERROR级别日志
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void generateErrorLog() {
        log.error("==>  自动产生ERROR级别日志，生产时间：{}", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
    }

    /**
     * 生成ERROR
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void generateError() {
        int errorRes = 1 / 0;
    }

}
```

## 关键配置

* config目录

在 `config` 目录下我们定义了日志 Filter，以及一个读取 IP 的类（略）

* logback配置文件

* yml配置

注意修改 rabbitmq 地址

## 测试

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/kibana-index-pattern.png)

接着就可以在可视化（Discover）中查看我们的索引了：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/spring-boot-elk-discover.png)


