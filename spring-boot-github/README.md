# 简介

这个服务是针对 [Github API V3](https://developer.github.com/v3/) 的一个简易教程，帮助大家理解 `Github API` 的一些使用。本服务采用了 `org.eclipse.egit.github.core` 这一个
第三方库来辅助实现对 `User`、`Repository` 的 `API` 调用

## 准备

* 引入依赖

```xml
   <dependency>
        <groupId>org.eclipse.mylyn.github</groupId>
        <artifactId>org.eclipse.egit.github.core</artifactId>
        <version>2.1.5</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.1.10</version>
    </dependency>
```

* 封装 `Rest` 接口

> 请参考项目代码，这里不做一一介绍，目录结构如下：

```text
└─src
    ├─main
    │  ├─java
    │  │  └─xyz
    │  │      └─rexlin600
    │  │          └─github
    │  │              ├─common  // 公共
    │  │              │  ├─apiparam
    │  │              │  ├─constant
    │  │              │  ├─enums
    │  │              │  └─statuscode
    │  │              ├─config  // 配置
    │  │              │  ├─filter
    │  │              │  └─runner   // 注入 Github Client
    │  │              ├─entity
    │  │              ├─exception
    │  │              └─rest    // Rest 接口
    │  │              └─GithubApplication（启动类）
    │  └─resources
    └─test
        └─java
            └─github    // 提供了 user、repository 的 http 请求封装
                └─repository.http
                └─user.http
```
