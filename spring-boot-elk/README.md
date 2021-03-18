# 说明

- 使用给出的 `docker-compose.yml` 启动（包含ELK、rabbitmq）
- 下载 `logstash-7.1.0`
- 运行 `logstash`：进入到 `logstash` 目录并运行命令 `bin/logstash -f common-rabbitmq.conf`
- 启动本程序，然后范围 `kibana` 的地址：`localhost:5601`

接着在 `管理` 中创建索引模式，如下图：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/kibana-index-pattern.png)

接着就可以在可视化（Discover）中查看我们的索引了：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/spring-boot-elk-discover.png)


