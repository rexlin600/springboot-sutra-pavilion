package xyz.rexlin600.aop.aspect.annotation.feign;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * Remote sys log service
 *
 * @author hekunlin
 */
@FeignClient(name = "localSvc", url = "${feign.biz.local}")
public interface RemoteSysLogService {

	/**
	 * Add r
	 *
	 * @param sysLog sys log
	 * @return the r
	 */
	@PostMapping
	R add(@RequestBody SysLog sysLog);

}