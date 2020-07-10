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
 * Common svc
 *
 * @author hekunlin
 */
@Slf4j
@Service
public class CommonSvc {

	/**
	 * Handler svc list
	 */
	private final List<HandlerSvc> handlerSvcList;
	/**
	 * Map
	 */
	private Map<Integer, HandlerSvc> map = new HashMap<>();

	/**
	 * Common svc
	 *
	 * @param handlerSvcList handler svc list
	 */
	@Autowired
	public CommonSvc(List<HandlerSvc> handlerSvcList) {
		this.handlerSvcList = handlerSvcList;
	}

	/**
	 * Init gateway route
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