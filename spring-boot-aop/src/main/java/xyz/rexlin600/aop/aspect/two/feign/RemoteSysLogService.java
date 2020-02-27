package xyz.rexlin600.aop.aspect.two.feign;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * Feign 方法
 * <p>
 * 这里也可以切换为调用远程服务来实现新增日志
 *
 * @author: rexlin600
 * @date: 2020-02-16
 */
@FeignClient(name = "localSvc", url = "${feign.biz.local}")
public interface RemoteSysLogService {

    /**
     * 保存日志
     *
     * @param sysLog 日志实体
     * @return
     */
    @PostMapping
    R add(@RequestBody SysLog sysLog);

}