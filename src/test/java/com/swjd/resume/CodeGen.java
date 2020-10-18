package com.swjd.resume;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;

public class CodeGen {
    public static void main(String[] args) {
        //构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();

        //获取当前目录
        String projectPath = System.getProperty("user.dir");
        //
//        File ff = new File(System.getProperty("user.dir"));
//        String path=ff+"\\"+"base-main";
        globalConfig.setOutputDir(projectPath+"/src/main/java");

        //作者
        globalConfig.setAuthor("凌空");
        //生成后是否打开 资源管理器
        globalConfig.setOpen(false);
        //是否覆盖
        globalConfig.setFileOverride(false);

        //去掉Service的I前缀
        globalConfig.setServiceName("%sService");
        //主键自增
        globalConfig.setIdType(IdType.ID_WORKER);
        //日期类型
        globalConfig.setDateType(DateType.ONLY_DATE);
        //配置swagger文档
        globalConfig.setSwagger2(false);
        //加入到生成器中
        autoGenerator.setGlobalConfig(globalConfig);


        //设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://121.89.209.198:3389/db_resume?userSSl=false&useUnicode=true?characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDbType(DbType.MYSQL);
        //加入到生成器中
        autoGenerator.setDataSource(dataSourceConfig);

        //配置包
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("graduate");
        packageConfig.setParent("com.swjd");
        packageConfig.setEntity("bean");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");

        autoGenerator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("user");//设置要映射的表

        //下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//自动lombok
        //设置逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(createTime);
        list.add(updateTime);
        strategy.setTableFillList(list);
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);//localhost:8080:hello_id_1
        autoGenerator.setStrategy(strategy);

        //执行
        autoGenerator.execute();
    }
}
