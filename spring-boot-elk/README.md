# 说明

- 我们使用 `SpringBoot` 项目集成 `logback` 打印日志，同时增加一个 `appender` 将日志发送到消息队列中；
- 然后利用 `logtash` 采集日志到 `ES` 集群；
- 最后通过 `kibanna` 展示日志。

这里的示例项目中我们使用定时任务来模拟产生各个级别的日志以及报错。

## Requirement

### 环境

- java
- docker、docker-compose
- logstash-7.1.0

### 准备工作

将项目中的 `docker-compose.yml`、`common-rabbitmq.conf` 文件拷贝到你想要存放的位置（这里就不赘述关于 docker 的相关知识了），然后执行下面步骤：

- 运行 docker-compose.yml（`docker run -f docker-compose.yml up -d`）
- 运行 logstash（`bin/logstash -f common-rabbitmq.conf`）

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

启动类加上 `@EnableScheduling` 注释，因为我们这个项目是使用定时任务来模拟产生日志数据的

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

## 配置

* config目录

在 `config` 目录下我们定义了日志 Filter 来负责我们产生日志时哪些日志需要被推导消息队列中；此外还有一个读取 IP 的类（略）

* yml配置

包含项目信息、MQ配置，注意测试时需要修改 rabbitmq 地址为自己的MQ服务器地址

## 测试

项目运行起来后我们可以在 kibana 上添加一个索引模式，如下：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/kibana-index-pattern.png)

接着就可以在可视化（Discover）中查看我们的索引了：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/spring-boot-elk-discover.png)
