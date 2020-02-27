package xyz.rexlin600.aop.aspect.two.config;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.rexlin600.aop.aspect.two.feign.RemoteSysLogService;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * @author: rexlin600
 * @date: 2020-02-27
 */
//@Configuration
public class SysLogConfig {

    @Autowired
    private SysLogService sysLogService;

    //@Bean
    //public RemoteSysLogService remoteSysLogService() {
    //    return new RemoteSysLogService() {
    //        @Override
    //        public R add(SysLog sysLog) {
    //            return R.ok(sysLogService.save(sysLog));
    //        }
    //    };
    //}

}