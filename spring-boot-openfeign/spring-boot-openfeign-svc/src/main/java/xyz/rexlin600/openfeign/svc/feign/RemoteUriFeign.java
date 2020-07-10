package xyz.rexlin600.openfeign.svc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.rexlin600.openfeign.svc.config.FeignConfiguration;

/**
 * Remote uri feign
 *
 * @author hekunlin
 */
@Component
@FeignClient(name = "remoteURI", url = "http://api.juheapi.com", configuration = FeignConfiguration.class)
public interface RemoteUriFeign {

	/**
	 * History toady string
	 *
	 * @param key   key
	 * @param v     v
	 * @param month month
	 * @param day   day
	 * @return the string
	 */
	@GetMapping("/japi/toh")
	String historyToady(@RequestParam(value = "key") String key,
						@RequestParam(value = "v") String v,
						@RequestParam(value = "month") Integer month,
						@RequestParam(value = "day") Integer day);

}