package com.qihai.job.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class BeanConfig {

	@Bean("druiddatasource")
	@ConfigurationProperties(prefix="spring.datasource.druid.log")
	public DataSource datasource() {
		return DruidDataSourceBuilder.create().build();
	}
	
}
