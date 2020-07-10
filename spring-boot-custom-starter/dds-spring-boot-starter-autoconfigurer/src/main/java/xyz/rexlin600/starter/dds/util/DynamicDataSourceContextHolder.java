package xyz.rexlin600.starter.dds.util;

import lombok.experimental.UtilityClass;

/**
 * Dynamic data source context holder
 *
 * @author hekunlin
 */
@UtilityClass
public class DynamicDataSourceContextHolder {

	/**
	 * Context holder
	 */
	private final ThreadLocal<Long> CONTEXT_HOLDER = new ThreadLocal<>();

	/**
	 * Push *
	 *
	 * @param dataSourceType data source type
	 */
	public void push(Long dataSourceType) {
		CONTEXT_HOLDER.set(dataSourceType);
	}

	/**
	 * Get long
	 *
	 * @return the long
	 */
	public Long get() {
		return CONTEXT_HOLDER.get();
	}

	/**
	 * Remove
	 */
	public void remove() {
		CONTEXT_HOLDER.remove();
	}

}