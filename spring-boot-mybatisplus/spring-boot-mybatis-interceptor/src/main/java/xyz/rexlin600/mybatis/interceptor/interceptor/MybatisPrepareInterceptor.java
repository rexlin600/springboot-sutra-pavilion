package xyz.rexlin600.mybatis.interceptor.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * Mybatis prepare interceptor
 *
 * @author hekunlin
 */
@Component
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MybatisPrepareInterceptor implements Interceptor {

	/**
	 * Intercept object
	 *
	 * @param invocation invocation
	 * @return the object
	 */
	@SneakyThrows
	@Override
	public Object intercept(Invocation invocation) {
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
			return invocation.proceed();
		}

		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String originalSql = boundSql.getSql();
		log.info("==>  MybatisPrepareInterceptor prepare 拦截器，原始SQL=[{}]", originalSql);
		Object parameterObject = boundSql.getParameterObject();
		log.info("==>  parameterObject is : {}", parameterObject);

		// TODO 进行 SQL 修改等操作
		String newSql = originalSql;

		metaObject.setValue("delegate.boundSql.sql", newSql);
		return invocation.proceed();
	}

	/**
	 * Plugin object
	 *
	 * @param target target
	 * @return the object
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * Sets properties *
	 *
	 * @param properties properties
	 */
	@Override
	public void setProperties(Properties properties) {
		log.info("==>  set properties ...");
	}

}