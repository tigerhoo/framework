package com.qihai.commerce.framework.datasource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration   
@ImportResource(locations={"classpath:config/application-*.xml"})   
public class DataSourceConfig {

}
