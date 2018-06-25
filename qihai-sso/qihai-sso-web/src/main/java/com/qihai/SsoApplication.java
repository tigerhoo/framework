package com.qihai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = {"com.qihai.sms.modules.*.dao"})
public class SsoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoApplication.class);
	}
}
