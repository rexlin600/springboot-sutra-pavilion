# 简介

`JAXB` 能够使用 `Jackson` 对 `JAXB` 注解的支持实现(`jackson-module-jaxb-annotations`)，既方便生成 `XML`，也方便生成 `JSON`，
这样一来可以更好的标志可以转换为 `JSON` 对象的 `JAVA` 类

`JAXB（Java Architecture for XML Binding) ` 是一个业界的标准，是一项可以根据 `XML Schema` 产生 `Java` 类的技术!!!

**缺省的数据类型绑定**

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/binding.jpg)

一般地，我们将 `Java` 对象转换为 `XML` 的过程称之为 `Marshal`，将 `XML` 转换为 `Java` 对象的过程称之为 `UnMarshal`

* JAXB 的 xjc 工具定义 schema 通过XSD（XML Schema Definition）文档生成Java类的方式
* Java Annotation 注解 JAXB通过分析java类中的标记（annotation），将java类转换为相应的XML文档。

JAXB 提供了如下几个类、接口供开发者与之交互。

```text
JAXBContext 类
应用的入口，用于管理XML/Java绑定信息。

Marshaller 接口
将Java对象序列化为XML数据。

Unmarshaller 接口
将XML数据反序列化为Java对象。
```

## 采坑

* 报错如: `类的两个属性具有相同名称`
```text
com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions

类的两个属性具有相同名称 "id"
	this problem is related to the following location:
		at public java.lang.Long xyz.rexlin600.jaxb.entity.simple.User.getId()
		at xyz.rexlin600.jaxb.entity.simple.User
	this problem is related to the following location:
		at private java.lang.Long xyz.rexlin600.jaxb.entity.simple.User.id
		at xyz.rexlin600.jaxb.entity.simple.User

	at com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException$Builder.check(IllegalAnnotationsException.java:91)
	at com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl.getTypeInfoSet(JAXBContextImpl.java:445)
	at com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl.<init>(JAXBContextImpl.java:277)
	at com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl.<init>(JAXBContextImpl.java:124)
	at com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl$JAXBContextBuilder.build(JAXBContextImpl.java:1123)
	at com.sun.xml.internal.bind.v2.ContextFactory.createContext(ContextFactory.java:147)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
```

这个问题是因为我使用了 `lombok`，这样在字段上添加 `@XmlElement` 后导致的（实质上你自己写getter、setter这个注解使用位置不对还是会报错），
解决办法如下：

- 手动生成 getter、setter
- 字段或 setter 上使用 `@XmlElement` 注解；getter 上统一增加 `@XmlTransient` 注解即可



## 参考

- [JAXB Users Guide](https://javaee.github.io/jaxb-v2/doc/user-guide/ch03.html)
- [JABX 教程](https://www.w3cschool.cn/jaxb2/)