package xyz.rexlin600.mybatisplus.codegen.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import xyz.rexlin600.mybatisplus.codegen.common.constant.CodeGenConstant;
import xyz.rexlin600.mybatisplus.codegen.entity.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * CodeGenUtils 实体类
 *
 * @author: hekunlin
 * @date: 2020/1/14
 */
public class CodeGenUtils {

    /**
     * 全局配置
     *
     * @param projectPath  项目生成路径
     * @param author       作者
     * @param openSwagger2 是否开启 Swagger2
     * @return
     */
    public static GlobalConfig getGenerator(String projectPath, String author, Boolean openSwagger2, DateType dateType) {
        GlobalConfig gc = new GlobalConfig();
        // 默认D盘根目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖文件：默认覆盖
        gc.setFileOverride(true);
        // 开发人员
        gc.setAuthor(author);
        // 是否打开输出目录
        gc.setOpen(false);
        // kotlin
        gc.setKotlin(false);
        // 开启 ActiveRecord 模式
        gc.setActiveRecord(false);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(false);
        // 开启 baseColumnList
        gc.setBaseColumnList(true);
        // 时间类型对应策略
        gc.setDateType(dateType);

        // 实体命名方式
        gc.setEntityName("%sDO");
        // mapper 命名方式
        gc.setMapperName("%sMapper");
        // Mapper xml 命名方式
        gc.setXmlName("%sMapper");
        // service 命名方式
        gc.setServiceName("%sService");
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        // controller 命名方式
        gc.setControllerName("%sController");
        // ID 生成策略
        gc.setIdType(IdType.AUTO);

        // 开启 swagger2 模式
        if (!ObjectUtils.isEmpty(openSwagger2)) {
            gc.setSwagger2(openSwagger2);
        }

        // 开启 BaseResultMap
        gc.setBaseResultMap(true);
        // 开启 baseColumnList
        gc.setBaseColumnList(true);
        return gc;
    }

    /**
     * 获取自定义配置
     *
     * @param projectPath
     */
    public static InjectionConfig getInjectConfig(String projectPath) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        String templatePath = CodeGenConstant.VELOCITY;
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String finalProjectPath = projectPath;
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名，如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化
                return finalProjectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 策略配置
     *
     * @param tablePrefix   表前缀
     * @param lombok        是否开启lombok
     * @param versionColumn 乐观锁
     * @param logicColumn   逻辑删除
     * @param list          要生成的表
     * @return
     */
    public static StrategyConfig getStrategyConfig(String tablePrefix, boolean lombok, String versionColumn, String logicColumn, List<String> list) {
        StrategyConfig strategy = new StrategyConfig();

        // 下划线转换驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);

        // 乐观锁属性名称
        if (!StringUtils.isEmpty(versionColumn)) {
            strategy.setVersionFieldName(versionColumn);
        }

        // 逻辑删除属性名称
        if (!StringUtils.isEmpty(logicColumn)) {
            strategy.setVersionFieldName(logicColumn);
        }

        /**
         * 自定义实体父类、接口父类、service父类、service实现类、mapper接口
         */
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setSuperControllerClass("com.baomidou.mybatisplus.extension.api.ApiController");
        strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");

        strategy.setEntityLombokModel(lombok);

        // 表前缀
        if (!StringUtils.isEmpty(tablePrefix)) {
            strategy.setTablePrefix(tablePrefix);
        }

        // 生成的表
        list.stream().toArray();
        String[] objects = list.toArray(new String[list.size()]);
        strategy.setInclude(objects);

        return strategy;
    }

    /**
     * 配置模板
     *
     * @return
     */
    public static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        /**
         * 配置自定义输出模板
         *
         * 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
         */
        templateConfig.setController("/templates/controller.java");
        templateConfig.setEntity("/templates/entity.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setXml(null);
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        return templateConfig;
    }

    /**
     * 包配置
     *
     * @param entityPath
     * @param mapperPath
     * @param svcPath
     * @param restPath
     * @param xmlPath
     * @return
     */
    public static PackageConfig getPackageConfig(String entityPath, String mapperPath,
                                                 String svcPath, String svcImplPath,
                                                 String restPath, String xmlPath) {
        PackageConfig pc = new PackageConfig();
        pc.setParent("")
                .setMapper(mapperPath)
                .setService(svcPath)
                .setServiceImpl(svcImplPath)
                .setController(restPath)
                .setEntity(entityPath)
                .setXml(xmlPath);
        return pc;
    }

    /**
     * 数据源配置
     *
     * @param dataSource
     * @return
     */
    public static DataSourceConfig getDatasource(DataSource dataSource) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + dataSource.getHost() + ":" + dataSource.getPort()
                + "/" + dataSource.getDbName() + "" +
                "?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName(CodeGenConstant.JDBC_DRIVER_CLASS_NAME);
        dsc.setUsername(AesUtils.decrypt(dataSource.getUsername()));
        dsc.setPassword(AesUtils.decrypt(dataSource.getPassword()));
        return dsc;
    }

    /**
     * 设置数据源
     *
     * @param dataSource
     * @return
     */
    public static HikariConfig getInjectDsConfig(DataSource dataSource) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + dataSource.getHost().trim() + ":" + dataSource.getPort().trim() + "/"
                + dataSource.getDbName().trim() + "?characterEncoding=utf8&serverTimezone=GMT%2B8");
        config.setUsername(AesUtils.decrypt(dataSource.getUsername()));
        config.setPassword(AesUtils.decrypt(dataSource.getPassword()));

        config.setAutoCommit(true);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(5);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return config;
    }

}