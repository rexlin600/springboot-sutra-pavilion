package xyz.rexlin600.mybatisplus.codegen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;
import xyz.rexlin600.mybatisplus.codegen.mapper.DataSourceMapper;
import xyz.rexlin600.mybatisplus.codegen.service.DataSourceService;

/**
 * Data source service
 *
 * @author hekunlin
 */
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {

}