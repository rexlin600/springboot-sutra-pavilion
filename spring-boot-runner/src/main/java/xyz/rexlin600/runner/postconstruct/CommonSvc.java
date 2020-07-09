package xyz.rexlin600.runner.postconstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.rexlin600.runner.postconstruct.handler.HandlerSvc;

import javax.annotation.PostConstruct;
import java.time.Clock;
import java.time.Instant;
import java.util.*;

/**
 * 网关初始化服务
 *
 * @author: hekunlin
 * @since: 2020/3/6
 */
@Slf4j
@Service
public class CommonSvc {

    private final List<HandlerSvc> handlerSvcList;
    private Map<Integer, HandlerSvc> map = new HashMap<>();

    @Autowired
    public CommonSvc(List<HandlerSvc> handlerSvcList) {
        this.handlerSvcList = handlerSvcList;
    }

    /**
     * 有时候存在这个需求：在类加载的时候，为当前类初始化一些数据，那么可以使用@PostConstruct注解，需要明确的是：
     * <p>
     * 1）他只会被调用一次且优先于 Runner 被调用，它更加针对当前文件而 Runner 服务于整个项目（可参考 readme 中 PostConstruct 的生命周期）
     * 2）在项目还没有启动成功的时候，@PostConstruct已经执行完了，因为@PostConstruct是在Init类注入完成后立马执行的，它并不依赖于项目的启动
     * 3）使用上：非静态方法、无参数、返回void、抛出已检查异常、只会被执行一次
     *
     * @PostConstruct 一般用于：1）初始化数据，如：字典、路由表、依赖注入完成后的Bean注入等 2）定时任务
     *
     * <p>
     * 执行顺序： 父类静态变量或静态语句块–>子类静态变量或静态语句块->父类实例变量或初始化语句块–>父类构造方法->子类实例变量或初始化语句块->子类构造方法--> @Autowired -> @PostConstruct....->destroy->@PreDestroy
     */
    @PostConstruct
    public void initGatewayRoute() {
        log.info("==>  初始化开始=[{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());

        for (HandlerSvc handlerSvc : handlerSvcList) {
            map.put(handlerSvc.handleCode(), handlerSvc);
        }

        log.info("CommonSvc has storage handler size=[{}]", map.size());

        // 测试使用 handler
        Set<Integer> keySet = map.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            HandlerSvc handlerSvc = map.get(iterator.next());
            // 字符串、数字处理器
            if (handlerSvc.handleCode().equals(1)) {
                handlerSvc.handle("String Handler");
            }
            if (handlerSvc.handleCode().equals(2)) {
                handlerSvc.handle(123);
            }
        }

        log.info("==>  初始化结束=[{}]", Instant.now(Clock.systemDefaultZone()).toEpochMilli());
    }

}