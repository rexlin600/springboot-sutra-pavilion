package xyz.rexlin600.aop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.mapper.SysLogMapper;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * Sys log service
 *
 * @author hekunlin
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
