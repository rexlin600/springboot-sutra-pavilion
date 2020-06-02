# Validation

> 区分 JSR-303 与 Hibernate Validator

`JSR-303` 是 `JAVA EE 6` 中的一项子规范，叫做 `Bean Validation`，`Hibernate Validator` 是 `Bean Validation` 的参考实现 。
`Hibernate Validator` 提供了 `JSR 303` 规范中所有内置 `constraint` 的实现，除此之外还有一些附加的 `constraint`。

目前最新版本是 `JSR380`，即 `Bean Validation 2.0`.

## Bean Validation 中的规范

| Constraint | 详细信息 |
| ---- | ----- |
| @Null	| 被注释的元素必须为 null |
| @NotNull	| 被注释的元素必须不为 null |
| @AssertTrue	| 被注释的元素必须为 true |
| @AssertFalse	| 被注释的元素必须为 false |
| @Min(value)	| 被注释的元素必须是一个数字，其值必须大于等于指定的最小值 |
| @Max(value)	| 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 |
| @DecimalMin(value)	| 被注释的元素必须是一个数字，其值必须大于等于指定的最小值 |
| @DecimalMax(value)	| 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 |
| @Size(max, min)	| 被注释的元素的大小必须在指定的范围内 |
| @Digits (integer, fraction)	| 被注释的元素必须是一个数字，其值必须在可接受的范围内 |
| @Past	| 被注释的元素必须是一个过去的日期 |
| @Future	| 被注释的元素必须是一个将来的日期 |
| @Pattern(value)	| 被注释的元素必须符合指定的正则表达式 |

## Hibernate Validator 附加的一些规范

| Constraint | 详细信息 |
| ---- | ----- |
| @Email | 被注释的元素必须是电子邮箱地址 |
| @Length | 被注释的字符串的大小必须在指定范围内 |
| @NotEmpty | 被注释的字符串必须非空 |
| @Range | 被注释的元素必须在合适的范围内 |

## 分组校验

分组校验中需要注意如果标记接口没有继承 `Default`，则不会校验没有添加分组的字段，反之则会校验！！！

## 参考

- [JSR 303: Bean Validation](https://jcp.org/en/jsr/detail?id=303)
- [JSR 303 - Bean Validation 介绍及最佳实践](https://www.ibm.com/developerworks/cn/java/j-lo-jsr303/index.html)
- [Groups验证遇到的坑](https://blog.csdn.net/keyliwen/article/details/80702633?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)
- [Hibernate Validator Documentation](http://hibernate.org/validator/documentation/)

