package xyz.rexlin600.mybatis.interceptor.config;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.mybatis.interceptor.interceptor.MybatisExecutorInterceptor;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * Mybatis interceptor config
 *
 * @author hekunlin
 */
@ConditionalOnBean({SqlSessionFactory.class})
@Configuration
@AutoConfigureAfter({PageHelperAutoConfiguration.class})
public class MybatisInterceptorConfig {

	/**
	 * Sql session factory list
	 */
	@Autowired
	private List<SqlSessionFactory> sqlSessionFactoryList;

	/**
	 * Add my interceptor
	 */
	@PostConstruct
	public void addMyInterceptor() {
		MybatisExecutorInterceptor interceptor = new MybatisExecutorInterceptor();
		Iterator var3 = this.sqlSessionFactoryList.iterator();
		while (var3.hasNext()) {
			SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) var3.next();
			sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
		}
	}
}