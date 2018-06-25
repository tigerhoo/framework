package com.qihai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
public class PermissionWebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/**") // 设置允许访问的接口路径，此处为允许访问/api/开头的所有接口
				.allowedOrigins("*") // 允许的域名，如http://www.baidu.com
				.allowedMethods("*") // 允许请求的方法,POST,GET等
				.allowedHeaders("*") // 允许的请求头
				// .exposedHeaders("Content-Type")//允许的响应头
				.allowCredentials(true) // 是否允许操作处理cookie
				.maxAge(3600L);// 预检有效时间，单位为秒，在有效期内不再次发送预检请求
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// swagger设置
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}