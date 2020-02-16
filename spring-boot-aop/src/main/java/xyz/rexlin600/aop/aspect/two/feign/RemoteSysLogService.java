package xyz.rexlin600.aop.aspect.two.feign;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import xyz.rexlin600.aop.entity.SysLog;

/**
 * Feign 方法
 *
 * @author: rexlin600
 * @date: 2020-02-16
 */
@FeignClient(contextId = "remoteSysLogService", value = "这里填入你的远程服务名称")
public interface RemoteSysLogService {

    /**
     * 保存日志
     *
     * @param sysLog 日志实体
     * @param from   是否内部调用
     * @return succes、false
     */
    @PostMapping("/log/save")
    R<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader("from") String from);
}