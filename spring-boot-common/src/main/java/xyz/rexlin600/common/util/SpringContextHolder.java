package xyz.rexlin600.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Spring context holder
 *
 * @author hekunlin
 */
@Slf4j
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	/**
	 * applicationContext
	 */
	private static ApplicationContext applicationContext = null;

	/**
	 * Gets application context *
	 *
	 * @return the application context
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	/**
	 * Sets application context *
	 *
	 * @param applicationContext application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * Gets bean *
	 *
	 * @param <T>  parameter
	 * @param name name
	 * @return the bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

	/**
	 * Gets bean *
	 *
	 * @param <T>          parameter
	 * @param requiredType required type
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	/**
	 * Clear holder
	 */
	public static void clearHolder() {
		if (log.isDebugEnabled()) {
			log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}


	/**
	 * Publish event *
	 *
	 * @param event event
	 */
	public static void publishEvent(ApplicationEvent event) {
		if (applicationContext == null) {
			return;
		}
		applicationContext.publishEvent(event);
	}

	/**
	 * Destroy
	 */
	@Override
	public void destroy() {
		SpringContextHolder.clearHolder();
	}

}
