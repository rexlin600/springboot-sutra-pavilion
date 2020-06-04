# common

这个 `Module` 用于存放常用的一些工具类、枚举类、常量等，方便快速查阅、使用，不涉及其他依赖包、业务！


## 数据脱敏工具使用

> 可以把它看成类似 `@JsonFormat` 这样的注解

1. 将 `xyz.rexlin600.common.util.sensitive` 下的文件引入
2. 在需要进行脱敏的数据字段上加上 `@Sensitive` 注解
3. 接口请求的数据在返回时会通过 `SensitiveSerialize` 自动序列化为脱敏数据

## 参考

- [@JsonFormat 实现原理](https://www.jianshu.com/p/1031c09da1db)
- [@JsonFormat、@JSONField、@DateTimeFormat的使用以及其区别](https://blog.csdn.net/qq_28483283/article/details/81326365)