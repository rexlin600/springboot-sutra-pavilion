# 简介

这里示例如何通过 `Mybatis` 拦截器去根据某些条件提前修改将要执行的 `SQL` 从而实现在原有代码不变的情况下对某些业务场景的 `SQL` 进行增强！

这样做的目的无非就是这些操作：拦截`SQL`、修改`SQL`、再执行、调整结果集、返回

## Mybatis 执行流程

> 这里我们先大概过一遍 Mybatis 的执行流程，后续将详细讲解流程中涉及的一些核心对象

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/mybatis%E6%89%A7%E8%A1%8C%E8%BF%87%E7%A8%8B.jpg)

从上图我们可以看出首先 `Mybatis` 创建 `Session` 对象；然后经由 `Executor` 判定使用何种 `Executor`（默认`SimpleExecutor`） 去处理对应语句；
最后由相应的的 `Handler` 实现 `SQL` 语句的处理、预编译、参数设置、类型映射、处理查询结果。


## Mybatis 核心对象

这里介绍 Mybatis 的核心对象，通过介绍这些核心对象，借助源码分析，一步一步梳理清除 `Mybatis` 的执行流程。

### SqlSessionFactory 与 SqlSession 接口

> SqlSessionFactory 主要定义了连接 Session、获取 Configuration 的方法；而 SqlSession 则定义了一些 SQL 语句、事务层面的方法。

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/sqlSession.jpg)

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/sqlsession-2.jpg)

这二者的实现类 `SqlSessionManager` 的构造器中则是通过动态代理的方式创建相应的 `SqlSession`，`SqlSessionManager` 的部分源码如下：

```java
    // SqlSessionManager 构造器
    private SqlSessionManager(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        // 动态代理创建 SqlSession
        this.sqlSessionProxy = (SqlSession)Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(), new Class[]{SqlSession.class}, new SqlSessionManager.SqlSessionInterceptor());
    }

    // 实例化 SqlSessionManager 方法
    public static SqlSessionManager newInstance(Reader reader) {
        return new SqlSessionManager((new SqlSessionFactoryBuilder()).build(reader, (String)null, (Properties)null));
    }

    // 省略其他的 newInstance 方法 ...
```

那么上面源码中传入的 `SqlSessionFactory` 对象从哪儿来呢？请接着往下看：

进一步剖析我们发现它是通过 `SqlSessionFactoryBuilder` 去 `build` 的，最终返回 `DefaultSqlSessionFactory`，`SqlSessionFactoryBuilder` 的部分源码如下：

```java
    // SqlSessionFactory build 方法
    public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
        SqlSessionFactory var5;
        try {
            // 通过 XMLConfigBuilder 读取配置
            XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
            var5 = this.build(parser.parse());
        } catch (Exception var14) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", var14);
        } finally {
            ErrorContext.instance().reset();

            try {
                reader.close();
            } catch (IOException var13) {
            }

        }

        return var5;
    }

    // 返回 DefaultSqlSessionFactory
    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

    // MybatisSqlSessionFactoryBuilder.class 中的 build 方法
    public SqlSessionFactory build(Configuration config) {
        MybatisConfiguration configuration = (MybatisConfiguration)config;
        GlobalConfig globalConfig = GlobalConfigUtils.getGlobalConfig(configuration);
        Object identifierGenerator;
        if (globalConfig.getIdentifierGenerator() == null) {
            if (null != globalConfig.getWorkerId() && null != globalConfig.getDatacenterId()) {
                identifierGenerator = new DefaultIdentifierGenerator(globalConfig.getWorkerId(), globalConfig.getDatacenterId());
            } else {
                identifierGenerator = new DefaultIdentifierGenerator();
            }

            globalConfig.setIdentifierGenerator((IdentifierGenerator)identifierGenerator);
        } else {
            identifierGenerator = globalConfig.getIdentifierGenerator();
        }

        IdWorker.setIdentifierGenerator((IdentifierGenerator)identifierGenerator);
        if (globalConfig.isEnableSqlRunner()) {
            (new SqlRunnerInjector()).inject(configuration);
        }

        SqlSessionFactory sqlSessionFactory = super.build(configuration);
        globalConfig.setSqlSessionFactory(sqlSessionFactory);
        return sqlSessionFactory;
    }
```

当我们经过上面一些列操作之后，我们获取到了 `SqlSessionFactory`，这时候就可以通过 `DefaultSqlSessionFactory` 去获取 `SqlSession` 对象，源码如下：

```java
    // 打开 Session
    public SqlSession openSession() {
            return this.openSessionFromDataSource(this.configuration.getDefaultExecutorType(), (TransactionIsolationLevel)null, false);
        }
    // 省略其他 openSession 方法 ...

    // 获取 SqlSession
    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;

        DefaultSqlSession var8;
        try {
            // 通过 Confuguration 对象获取 Mybatis 的相关配置信息, Environment对象包含了数据源和事务的配置
            Environment environment = this.configuration.getEnvironment();
            TransactionFactory transactionFactory = this.getTransactionFactoryFromEnvironment(environment);
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            // 这里会创建一个 Executor，详细过程下面源码
            Executor executor = this.configuration.newExecutor(tx, execType);
            // 返回 DefaultSqlSession，有了这个之才方便后续借助 Executor 去执行 SQL 语句
            // 具体可以看下 DefaultSqlSession 中的源码，你会发现，最终还是通过 `this.executor.XXX` 去执行的！
            var8 = new DefaultSqlSession(this.configuration, executor, autoCommit);
        } catch (Exception var12) {
            this.closeTransaction(tx);
            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + var12, var12);
        } finally {
            ErrorContext.instance().reset();
        }

        return var8;
    }
```

### Executor 接口

> 前面分析创建 SqlSession 的接口，实际上已经剧透了 Executor

首先来看下 `Mybatis` 中 `Executor` 的接口及实现类：

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/mybatis-executor.jpg)

从上面的源码分析中，我们可以看到有这么一段方法：

```java
// 省略部分源码 ...
Executor executor = this.configuration.newExecutor(tx, execType);
// 省略部分源码 ...
```

那么我们进一步探查 `newExecutor` 这个方法，可以看到源码如下：

```java
    // MybatisConfiguration.class 中创建 Executor 
    public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
        executorType = executorType == null ? this.defaultExecutorType : executorType;
        // 这里就是为什么前面的图片说默认是 SimpleExecutor 的原因
        executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
        Object executor;
        if (ExecutorType.BATCH == executorType) {
            executor = new MybatisBatchExecutor(this, transaction);
        } else if (ExecutorType.REUSE == executorType) {
            executor = new MybatisReuseExecutor(this, transaction);
        } else {
            executor = new MybatisSimpleExecutor(this, transaction);
        }

        if (this.cacheEnabled) {
            executor = new MybatisCachingExecutor((Executor)executor);
        }

        Executor executor = (Executor)this.interceptorChain.pluginAll(executor);
        return executor;
   }
```

这也就解释了我们前面的图片说默认 `Executor` 是 `SimpleExecutor`。

## SpringBoot 中自定义 Interceptor（集成 PageHelper 分页插件）

对于业务中需要用到 `Mybatis` 进行拦截处理的，常用业务包括：

* 大 `SQL` 拦截
* `SQL` 操作日志采集
* 分页
* 拦截 `SQL` 用于权限验证、处理等
* 其他 `SQL` 预处理

在 `Mybatis` 中主要提供下面 `4` 个接口支持拦截：

| 拦截 | 方法 | CH |
| --- | --- | --- |
| `Executor` | update, query, flushStatements, commit, rollback, getTransaction, close, isClosed | 最先被执行，查询、更新操作，如果要指定哪种操作拦截，使用这个方法 |
| `ParameterHandler` | getParameterObject, setParameters | SQL参数处理用这个方法、包括mapper配置信息也可以获取到 |
| `StatementHandler` | prepare, parameterize, batch, update, query | JDBC和数据库交互，连接信息存在 |
| `ResultSetHandler` | handleResultSets, handleOutputParameters | 结果集处理 |

如果集成了 `PageHelper` 做分页，那么实现 `Mybatis` 拦截器需要拦截 `Executor` ，即使用 `QueryInterceptor`。同时需要注意的时这个拦截器要满足一定的规范，具体参考文末的两篇参考文章：`重要：Executor 拦截器高级教程 - QueryInterceptor 规范`、`重要：SpringBoot 通过自定义 Mybatis 拦截器，实现 SQL 的改写`

**具体操作：**

1. 定义拦截器 `MybatisExecutorInterceptor` 并完成相应的各种处理
2. 编写配置类 `MybatisInterceptorConfig`
3. 编写 `META-INF` 下的 `spring.factories`

## 参考

- [Mybatis深入浅出系列](https://www.cnblogs.com/dongying/tag/Mybatis%E6%B7%B1%E5%85%A5%E6%B5%85%E5%87%BA%E7%B3%BB%E5%88%97/)
- [MyBatis自定义拦截器插件](https://blog.csdn.net/x763795151/article/details/87886492)
- [重要：Executor 拦截器高级教程 - QueryInterceptor 规范](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/Interceptor.md)
- [重要：SpringBoot 通过自定义 Mybatis 拦截器，实现 SQL 的改写](https://www.cnblogs.com/d0usr/p/12448639.html)
- [重要：mybatis - 基于拦截器修改执行中的SQL语句](https://www.cnblogs.com/cjunn/p/12168421.html#/cnblog/works/article/12168421)

![](https://rexlin600-blog.oss-cn-chengdu.aliyuncs.com/sqlSession.jpg)