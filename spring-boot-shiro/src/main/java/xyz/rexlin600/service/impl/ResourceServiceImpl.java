package xyz.rexlin600.service.impl;

import xyz.rexlin600.entity.Resource;
import xyz.rexlin600.mapper.ResourceMapper;
import xyz.rexlin600.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
