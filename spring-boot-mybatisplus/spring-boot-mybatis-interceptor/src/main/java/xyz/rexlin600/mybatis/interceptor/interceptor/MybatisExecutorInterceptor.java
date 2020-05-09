package xyz.rexlin600.mybatis.interceptor.interceptor;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReferenceUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import xyz.rexlin600.mybatis.interceptor.annotation.InjectSql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

/**
 * Mybatis 自定义拦截器
 *
 * @author: hekunlin
 * @date: 2020/3/24
 */
@Component
@Slf4j
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class MybatisExecutorInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("==>  Mybatis Interceptor 触发 ...");

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;

        // sql语句类型 select、delete、insert、update
        String sqlCommandType = ms.getSqlCommandType().toString();

        // 仅拦截 select 查询
        if (!sqlCommandType.equals(SqlCommandType.SELECT.toString())) {
            return invocation.proceed();
        }

        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }

        // FIXME 判断是否存在SQL注入注解：如果存在方法名称相同的则可能出现判断错误，这里只是为了演示在使用 pageHelper 分页的情况下的拦截器的使用
        String id = ms.getId();
        String mName = id.substring(id.lastIndexOf(".") + 1, id.length());
        Class<?> aClass = Class.forName(id.substring(0, id.lastIndexOf(".")));
        Method[] methods = aClass.getDeclaredMethods();
        if (CollectionUtil.isEmpty(Arrays.asList(methods))) {
            return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        }

        Optional<Method> optional = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(InjectSql.class) && m.getName().equals(mName)).findFirst();
        if (BooleanUtil.isFalse(optional.isPresent())) {
            return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        }

        Method method = optional.get();
        InjectSql annotation = method.getAnnotation(InjectSql.class);
        if (annotation.flag()) {
            // 组装新的 sql
            log.info("==>  原始SQL: {}", boundSql.getSql());
            if (!StringUtils.isEmpty(boundSql.getSql())) {
                String newSql = boundSql.getSql() + " limit 1";
                log.info("==>  改写的SQL: {}", newSql);
                modify(boundSql, "sql", newSql);
            }
        }

        return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    @Override
    public Object plugin(Object target) {
        log.info("==>  plugin方法：{}", target.toString());
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // 获取属性
        // String value1 = properties.getProperty("prop1");
        log.info("==>  properties方法：{}", properties.toString());
    }

    private static void modify(Object object, String fieldName, Object newFieldValue) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(object, newFieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}