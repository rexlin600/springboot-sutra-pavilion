# Skywalking

集成 `skywalking` 比较简单，可直接参考官方给的教程：[Setup java agent](https://github.com/apache/skywalking/blob/v7.0.0/docs/en/setup/service-agent/java-agent/README.md)

## 集成步骤

- 下载 `releases` 包
- 运行 `sh startup.sh` 启动服务端
- 找到 `agent` 这个目录
- 修改配置 `config/agent.config` 下的 `agent.service_name`、`collector.backend_service`
- 增加 `-javaagent:/path/to/skywalking-package/agent/skywalking-agent.jar` 到 `JVM` 启动参数中

具体请参考官方教程，集成完成之后可访问 `<your ip>:11800`，如下：

![](http://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/2020-05-03-050620.png)

![](http://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/2020-05-03-050739.png)

![](http://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/2020-05-03-050753.png)

## 某云高速下载

链接: https://pan.baidu.com/s/1XuZH16l9hV5K8yea6mzA9g  密码: 8i4f


## 参考

 - [skywalking 下载地址](http://skywalking.apache.org/downloads/)
