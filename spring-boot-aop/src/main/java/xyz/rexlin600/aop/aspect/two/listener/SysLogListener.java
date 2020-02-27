package xyz.rexlin600.aop.aspect.two.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xyz.rexlin600.aop.aspect.two.feign.RemoteSysLogService;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * 异步监听日志事件
 *
 * @author rexlin600
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Component
@AllArgsConstructor
public class SysLogListener implements ApplicationListener<SysLogEvent> {

    private final RemoteSysLogService remoteSysLogService;

    @Override
    public void onApplicationEvent(SysLogEvent event) {
        SysLog source = (SysLog) event.getSource();
        long timestamp = event.getTimestamp();
        log.info("==>  注解版本：处理监听事件参数:{}, 时间戳:{}", source.toString(), timestamp);
        // 模拟调用远程服务实现增加操作日志
        remoteSysLogService.add(source);
    }
}
