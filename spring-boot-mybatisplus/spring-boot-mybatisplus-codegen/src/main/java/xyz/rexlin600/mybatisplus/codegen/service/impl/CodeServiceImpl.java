package xyz.rexlin600.mybatisplus.codegen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.rexlin600.mybatisplus.codegen.common.constant.CodeGenConstant;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;
import xyz.rexlin600.mybatisplus.codegen.service.DataSourceService;
import xyz.rexlin600.mybatisplus.codegen.util.CodeGenUtils;
import xyz.rexlin600.mybatisplus.codegen.util.OsUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hekunlin
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class CodeServiceImpl implements CodeService {

    /**
     * HikariConfig & HikariDataSource
     */
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private final DataSourceService dataSourceService;

    @Autowired
    public CodeServiceImpl(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    /**
     * 分页查询表
     *
     * @param page      分页参数
     * @param size      分页参数
     * @param tableName 表名称
     * @param id        数据源ID
     * @return
     */
    @Override
    public R<Page<TableMetaData>> page(Integer page, Integer size, String tableName, Long id) throws SQLException {
        // check DataSource
        DataSource dataSource = dataSourceService.getOne(new LambdaQueryWrapper<DataSource>().eq(true, DataSource::getId, id));
        if (ObjectUtils.isEmpty(dataSource)) {
            log.error("==>  未找到 [{}] 对应的数据源配置", id);
            return R.failed("查询失败");
        }

        // 获取 HikariCP 数据源连接池
        config = CodeGenUtils.getInjectDsConfig(dataSource);
        ds = new HikariDataSource(config);

        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            log.error("==>  建立数据库连接失败 [{}]", e.getMessage());
            return R.failed("查询失败");
        }

        List<TableMetaData> list = new ArrayList<>();
        ResultSet queryRs = null;
        ResultSet countRs = null;
        Long total = 0L;

        long start = (page - 1) > 0 ? (page - 1) : 0;
        String querySql = CodeGenConstant.BASE_QUERY_TABLE_SQL + CodeGenConstant.LIMIT + start + "," + size;
        String countSql = CodeGenConstant.COUNT_QUERY_TABLE_SQL;

        try {
            // 统计总数
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            countRs = countStatement.executeQuery(countSql);
            while (countRs.next()) {
                total = countRs.getLong("total");
            }

            // 查询记录
            PreparedStatement queryStatement = connection.prepareStatement(querySql);
            queryRs = queryStatement.executeQuery(querySql);
            while (queryRs.next()) {
                TableMetaData metaData = TableMetaData.builder()
                        .tableName(queryRs.getString("table_name"))
                        .tableComment(queryRs.getString("table_comment"))
                        .engine(queryRs.getString("engine"))
                        .tableCollation(queryRs.getString("table_collation"))
                        .createTime(queryRs.getString("create_time"))
                        .updateTime(queryRs.getString("update_time"))
                        .build();
                list.add(metaData);
            }
        } catch (
                SQLException e) {
            log.error("==>  执行SQL异常 [{}]", e.getMessage());
            e.printStackTrace();
            return R.failed("执行SQL异常");
        } finally {
            try {
                countRs.close();
                queryRs.close();
                ds.close();
                connection.close();
            } catch (SQLException e) {
                log.error("==>  关闭连接池异常 [{}]", e.getMessage());
                throw new SQLException("关闭连接池异常");
            }
        }

        Page iPage = new Page<>();
        iPage.setCurrent(page);
        iPage.setSize(size);
        iPage.setTotal(total);
        iPage.setRecords(list);

        return R.ok(iPage);
    }


    /**
     * 生成代码
     *
     * @param codeGenReq
     * @return
     */
    @Override
    public R generate(CodeGenReq codeGenReq) {
        // 项目生成地址默认为系统桌面地址
        String projectPath = OsUtils.getDesktopPath();
        if (OsUtils.isOsLinux()) {
            projectPath = CodeGenConstant.LINUX_PATH;
        }
        log.info("==>  生成代码的位置=【{}】", projectPath);
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc;
        DataSourceConfig dsc;
        PackageConfig pc;
        InjectionConfig injectConfig;
        TemplateConfig templateConfig;
        StrategyConfig strategy;
        try {
            // 全局配置
            gc = CodeGenUtils.getGenerator(projectPath, codeGenReq.getAuthor(), codeGenReq.getOpenApiDoc());

            // 数据源配置
            DataSource dataSource = dataSourceService.getOne(new LambdaQueryWrapper<DataSource>().eq(true, DataSource::getId, codeGenReq.getId()));
            if (ObjectUtils.isEmpty(dataSource)) {
                log.error("==>  未找到 [{}] 对应的数据源配置", codeGenReq.getId());
                return R.failed("生成代码失败");
            }
            dsc = CodeGenUtils.getDatasource(dataSource);

            // 包配置
            pc = CodeGenUtils.getPackageConfig(codeGenReq.getPackageName());

            // 自定义配置
            injectConfig = CodeGenUtils.getInjectConfig(projectPath);
            // 配置模板
            templateConfig = CodeGenUtils.getTemplateConfig();

            // 策略配置
            String prefix = codeGenReq.getTablePrefix();
            boolean lombok = codeGenReq.isLombok();
            String version = codeGenReq.getVersionColumn();
            String logic = codeGenReq.getLogicColumn();
            List<String> list = codeGenReq.getList();
            strategy = CodeGenUtils.getStrategyConfig(prefix, lombok, version, logic, list);
        } catch (Exception e) {
            log.error("==>  配置异常 {}", e.getMessage());
            return R.failed("生成代码失败");
        }

        // 注入 mbg
        mpg.setGlobalConfig(gc)
                .setDataSource(dsc)
                .setPackageInfo(pc)
                .setCfg(injectConfig)
                .setTemplate(templateConfig)
                .setStrategy(strategy)
                .setTemplateEngine(new VelocityTemplateEngine());

        // 执行生成
        try {
            mpg.execute();
        } catch (Exception e) {
            log.error("==>  生成代码异常 [{}]", e.getMessage());
            return R.failed("生成代码失败");
        }

        log.info("==>  生成代码成功，生成的表 {}", codeGenReq.getList().toString());
        return R.ok("生成代码成功");
    }


}