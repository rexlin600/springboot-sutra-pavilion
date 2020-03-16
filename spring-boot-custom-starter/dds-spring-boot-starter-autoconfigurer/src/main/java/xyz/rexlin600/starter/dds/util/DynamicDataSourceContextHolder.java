package xyz.rexlin600.starter.dds.util;

import lombok.experimental.UtilityClass;

/**
 * 根据当前线程来选择具体的数据源
 *
 * @author rexlin600
 */
@UtilityClass
public class DynamicDataSourceContextHolder {

    private final ThreadLocal<Long> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 提供给AOP去设置当前的线程的数据源的信息
     *
     * @param dataSourceType
     */
    public void push(Long dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     * 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
     *
     * @return
     */
    public Long get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 使用默认的数据源
     */
    public void remove() {
        CONTEXT_HOLDER.remove();
    }

}