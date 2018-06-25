package com.qihai.commerce.framework.datasource.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.qihai.commerce.framework.datasource.DynamicDataSource;

@Configuration
@EnableAutoConfiguration
public class MybatisPlusConfig {
	
	//@Value("${mybatis.spring.base.package}")
	private static String basePackage = "com.qihai.**.dao;com.qihai.**.mapper";//不能加载TODO
	
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //log.info("初始化MapperScannerConfigurer");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

    //    配置事务管理
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "dataSource")DynamicDataSource dataSource) {
        //log.info("初始化DataSourceTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }
}
