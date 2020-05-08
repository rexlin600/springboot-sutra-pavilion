# fastDFS

`FastDFS` 是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。

`FastDFS` 为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用 `FastDFS` 很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。

## 简介

`FastDFS` 服务端有两个角色：跟踪器（`tracker`）和存储节点（`storage`）

* 跟踪器主要做调度工作，在访问上起负载均衡的作用。
* 存储节点存储文件，完成文件管理的所有功能：就是这样的存储、同步和提供存取接口，`FastDFS` 同时对文件的metadata进行管理。所谓文件的` meta data` 就是文件的相关属性，以键值对（`key valuepair`）方式表示，如：`width=1024`，其中的 `key`为 `width`，`value` 为 `1024`。文件metadata是文件属性列表，可以包含多个键值对。

## FastDFS 存储策略

为了支持大容量，存储节点（`服务器`）采用了分卷（或`分组`）的组织方式。存储系统由一个或多个卷组成，卷与卷之间的文件是相互独立的，所有卷的文件容量累加就是整个存储系统中的文件容量。一个卷可以由一台或多台存储服务器组成，一个卷下的存储服务器中的文件都是相同的，卷中的多台存储服务器起到了冗余备份和负载均衡的作用。

在卷中增加服务器时，同步已有的文件由系统自动完成，同步完成后，系统自动将新增服务器切换到线上提供服务。当存储空间不足或即将耗尽时，可以动态添加卷。只需要增加一台或多台服务器，并将它们配置为一个新的卷，这样就扩大了存储系统的容量。

## 交互过程

**上传交互：**

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/fastdfs-upload.jpg)

**下载交互：**

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/fastdfs-dowload.jpg)

## fastDFS 的文件同步

写文件时，客户端将文件写至 `group` 内一个 `storage server` 即认为写文件成功，`storage server` 写完文件后，会由后台线程将文件同步至` 同group` 内其他的 `storage server`。

每个 `storage` 写文件后，同时会写一份 `binlog`，`binlog` 里不包含文件数据，只包含文件名等元信息，这份 `binlog` 用于后台同步，`storage `会记录向 `group`内其他 `storage` 同步的进度，以便重启后能接上次的进度继续同步；进度以时间戳的方式进行记录，所以最好能保证集群内所有 `server` 的时钟保持同步。

`storage` 的同步进度会作为元数据的一部分汇报到 `tracker` 上，`tracke` 在选择读 `storage` 的时候会以同步进度作为参考。

## FastDFS 为什么要结合 Nginx

我们在使用 `FastDFS` 部署一个分布式文件系统的时候，通过 `FastDFS` 的客户端 `API` 来进行文件的上传、下载、删除等操作。同时通过 `FastDFS` 的 `HTTP` 服务器来提供 `HTTP` 服务。但是 `FastDFS` 的 `HTTP` 服务较为简单，无法提供负载均衡等高性能的服务，我们使用 `FastDFS` 的 `Nginx` 模块来弥补这一缺陷。

`FastDFS` 通过 `Tracker` 服务器,将文件放在 `Storage` 服务器存储,但是同组之间的服务器需要复制文件,有延迟的问题.假设 `Tracker` 服务器将文件上传到了 `192.168.1.80`,文件 `ID` 已经返回客户端,这时,后台会将这个文件复制到 `192.168.1.30`,如果复制没有完成,客户端就用这个 `ID` 在 `192.168.1.30` 取文件,肯定会出现错误。这个 `fastdfs-nginx-module` 可以重定向连接到源服务器取文件,避免客户端由于复制延迟的问题,出现错误。

## 安装教程

略，网上一搜一大把

## SpringBoot 集成 fastDFS

1. 引入依赖（我这里使用的是 `com.github.tobato` 提供的 `fast-client`，就不再使用官方的包去再做封装了）

```xml
    <!-- 基于yuqih发布的代码与fastdfs-client 官方1.26版本的重构 -->
    <!-- https://mvnrepository.com/artifact/com.github.tobato/fastdfs-client -->
    <dependency>
        <groupId>com.github.tobato</groupId>
        <artifactId>fastdfs-client</artifactId>
        <version>1.26.6</version>
    </dependency>
```

2. 编写 `configBean`

```java
@Data
@Configuration
@ConfigurationProperties(prefix = "biz.fastdfs")
public class FastDfsConfigBean {

    /**
     * 文件访问地址
     */
    private String httpUrl;

}
```

3. 编写yml配置

```yaml
# fastDFS config
biz:
  fastdfs:
    httpUrl: http://10.197.236.171/
fdfs:
  connect-timeout: 600
  pool:
    max-total: 153
    max-wait-millis: 102
  so-timeout: 1500
  thumb-image:
    height: 150
    width: 150
  tracker-list:
    - 10.197.236.172:22122
    - 10.197.236.188:22122
```

4. 业务中直接引入 `client` 直接使用即可，具体请参考 `FastDfsRest.java` 文件

## 参考

- [FastDFS高可用集群架构配置搭建](https://www.cnblogs.com/sunnydou/p/49b92d511047f4f9da6cd727cfd415d5.html)