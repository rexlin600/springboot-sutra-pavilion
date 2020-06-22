# 简介

集成如下第三方 `OSS`：

- 阿里云
- 七牛云
- 腾讯云

1. 引入 SDK

```xml
    <!-- aliyun oss -->
    <dependency>
        <groupId>com.aliyun.oss</groupId>
        <artifactId>aliyun-sdk-oss</artifactId>
        <version>${aliyun.oss.version}</version>
    </dependency>
    
    <!-- tencent cos -->
    <dependency>
        <groupId>com.qcloud</groupId>
        <artifactId>cos_api</artifactId>
        <version>5.6.24</version>
    </dependency>
    
    <!-- qiniuyun -->
    <dependency>
        <groupId>com.qiniu</groupId>
        <artifactId>qiniu-java-sdk</artifactId>
        <version>7.2.29</version>
    </dependency>
```

## 使用

- 引入 `OssFactory`
- 构建对应的 `OSS` 存储服务，`ossFactory.build(ossType);`
- 调用相应的方法