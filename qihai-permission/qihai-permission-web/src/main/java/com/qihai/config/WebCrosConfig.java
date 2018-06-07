package com.qihai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求全局设置.
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableWebMvc
public class WebCrosConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/pms/**") // 设置路径
				.allowedOrigins("*") // 允许的域名，如http://www.baidu.com
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 允许请求的方法
				.allowedHeaders("Content-Type","Accept") // 允许的请求头
				.exposedHeaders("Content-Type").allowCredentials(true).maxAge(3600);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}