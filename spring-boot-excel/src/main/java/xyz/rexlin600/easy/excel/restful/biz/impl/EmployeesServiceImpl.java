package xyz.rexlin600.easy.excel.restful.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.rexlin600.easy.excel.restful.biz.EmployeesService;
import xyz.rexlin600.easy.excel.restful.mapper.EmployeesMapper;
import xyz.rexlin600.easy.excel.restful.model.EmployeesDO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rexlin600
 * @since 2020-08-06
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, EmployeesDO> implements EmployeesService {

}