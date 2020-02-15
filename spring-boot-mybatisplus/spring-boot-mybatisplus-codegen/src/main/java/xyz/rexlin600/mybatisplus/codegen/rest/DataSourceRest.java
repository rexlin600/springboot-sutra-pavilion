package xyz.rexlin600.mybatisplus.codegen.rest;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.codegen.common.constant.CodeGenConstant;
import xyz.rexlin600.mybatisplus.codegen.common.req.DataSourceReq;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;
import xyz.rexlin600.mybatisplus.codegen.service.DataSourceService;
import xyz.rexlin600.mybatisplus.codegen.util.AesUtils;

import java.util.Date;


/**
 * @menu 代码生成-数据源CURD
 * @author: hekunlin
 * @date: 2020/1/14
 */
@RestController
@RequestMapping("/ds")
public class DataSourceRest {

    private final DataSourceService dataSourceService;

    @Autowired
    public DataSourceRest(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }


    /**
     * 新增数据源
     *
     * @param dataSourceReq
     * @return
     */
    @PostMapping
    public R saveDataSource(@RequestBody DataSourceReq dataSourceReq) {
        DataSource source = new DataSource();
        source = BeanCopier.create(dataSourceReq, source, new CopyOptions().setIgnoreNullValue(true)).copy();
        source.setCreateTime(new Date());
        // AES加密
        source.setUsername(AesUtils.encrypt(dataSourceReq.getUsername()));
        source.setPassword(AesUtils.encrypt(dataSourceReq.getPassword()));
        dataSourceService.save(source);

        return R.ok(CodeGenConstant.SUCCESS);
    }

    /**
     * 删除数据源
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteDataSource(@PathVariable(value = "id") Long id) {
        dataSourceService.removeById(id);
        return R.ok(CodeGenConstant.SUCCESS);
    }

    /**
     * 修改数据源
     *
     * @param dataSourceReq
     * @return
     */
    @PutMapping
    public R updateDataSource(@RequestBody DataSourceReq dataSourceReq) {
        DataSource source = new DataSource();
        // AES加密
        dataSourceReq.setUsername(AesUtils.encrypt(dataSourceReq.getUsername()));
        dataSourceReq.setPassword(AesUtils.encrypt(dataSourceReq.getPassword()));

        source = BeanCopier.create(dataSourceReq, source, new CopyOptions().setIgnoreNullValue(true)).copy();
        source.setUpdateTime(new Date());
        dataSourceService.updateById(source);

        return R.ok(CodeGenConstant.SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    public R pageDataSource(@RequestParam(value = "page") Integer page,
                            @RequestParam(value = "size") Integer size) {
        Page<DataSource> sourcePage = dataSourceService.page(new Page<DataSource>(page, size));

        return R.ok(sourcePage);
    }

    /**
     * 根据ID查询数据源
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getDataSource(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<DataSource> queryWrapper = new LambdaQueryWrapper<DataSource>()
                .eq(true, DataSource::getId, id);
        DataSource dataSource = dataSourceService.getOne(queryWrapper);

        return R.ok(dataSource);
    }

}