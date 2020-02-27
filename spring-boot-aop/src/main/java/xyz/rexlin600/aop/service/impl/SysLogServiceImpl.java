package xyz.rexlin600.aop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.aop.entity.SysLog;
import xyz.rexlin600.aop.mapper.SysLogMapper;
import xyz.rexlin600.aop.service.SysLogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-02-16
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}