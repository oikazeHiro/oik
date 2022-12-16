package com.oik.api.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YE
 * mybatis-plus代码生成器
 */
@Component
@Configuration
public class mybatisPlusGenerate {
    /**
     * 项目路径
     */
    private static final String PARENT_DIR = System.getProperty("user.dir");
    /**
     * 基本路径
     */
    private static final String SRC_MAIN_JAVA = "/src/main/java/com/oik/";
    /**
     * xml路径
     */
    private static final String XML_PATH = PARENT_DIR + "/dao"+SRC_MAIN_JAVA+"dao/xml";
    /**
     * entity路径
     */
    private static final String ENTITY_PATH = PARENT_DIR + "/dao"+SRC_MAIN_JAVA+"dao/entity";
    /**
     * mapper路径
     */
    private static final String MAPPER_PATH = PARENT_DIR + "/dao"+SRC_MAIN_JAVA+"dao/mapper";
    /**
     * service路径
     */
    private static final String SERVICE_PATH = PARENT_DIR + "/service"+SRC_MAIN_JAVA+"service/service";
    /**
     * serviceImpl路径
     */
    private static final String SERVICE_IMPL_PATH = PARENT_DIR + "/service"+SRC_MAIN_JAVA+"service/impl";
    /**
     * controller路径
     */
    private static final String CONTROLLER_PATH = PARENT_DIR + "/api"+SRC_MAIN_JAVA+"api/controller";

    static String URL = "jdbc:mysql://127.0.0.1:13306/shiro_test?characterEncoding=utf-8&useSSL=false&useTimezone=true&allowPublicKeyRetrieval=true";

    static String USERNAME = "root";
    static String PASSWORD = "root";
    static String AUTHOR = "oik";           //作者

    static String PROJECT_DIR = System.getProperty("user.dir") + "";  //非分布式项目，不用改变+号后面的值
    static String BASE_DIR = "";
    static String PACKAGE_DIR = "com.oik";
    //根据自己的包名设定第二个参数（框架中有且只能有一层--不能有多余的点）

    //反向生成的表
    static String[] tables = new String[]{

            "sys_greet",

    };

    public static void main(String[] args) {
        //基本配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD)
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .typeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        String type = fieldType.toLowerCase();
                        if (type.contains("date")) {
                            return DbColumnType.DATE;
                        } else if (type.contains("time")) {
                            return DbColumnType.TIME;
                        } else if (type.contains("tinyint")) {
                            return DbColumnType.INTEGER;
                        } else {
                            return super.processTypeConvert(globalConfig, fieldType);
                        }
                    }
                }).build();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(PROJECT_DIR + BASE_DIR)
                .author(AUTHOR)
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();

        //包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent(PACKAGE_DIR)
                .entity("dao.entity")
                .service("service.service")
                .serviceImpl("service.impl")
                .mapper("dao.mapper")
                .xml("dao.xml")
                .controller("api.controller")
                .pathInfo(getPathInfo())
                .build();

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();

        // 注入配置
        InjectionConfig injectionConfig = new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, objectMap) -> System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size()))
                .build();

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude(tables)
                .addTablePrefix("t_", "c_","sys_")
                .entityBuilder()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .formatFileName("%s")
                .enableLombok()
                .controllerBuilder()
                .enableRestStyle()
                .formatFileName("%sController")
                .serviceBuilder()
                .superServiceClass("com.github.yulichang.base.service.MPJJoinService")
                .superServiceImplClass("com.github.yulichang.base.MPJBaseServiceImpl")
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .mapperBuilder()
                .superClass("com.github.yulichang.base.MPJBaseMapper")
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .enableBaseColumnList()
                .enableBaseResultMap()

                .build();

        //装配配置，并生成代码
        new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .packageInfo(packageConfig)
                .template(templateConfig)
                .injection(injectionConfig)
                .strategy(strategyConfig)
                .execute();
    }

    /**
     * 获取路径信息map
     *
     * @return {@link Map < OutputFile, String> }
     * @author MK
     * &#064;date  2022/4/21 21:21
     */
    private static Map<OutputFile, String> getPathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.service, SERVICE_PATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        return pathInfo;
    }
}
