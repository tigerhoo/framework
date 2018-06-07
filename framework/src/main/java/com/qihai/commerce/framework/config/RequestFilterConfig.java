package com.qihai.commerce.framework.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qihai.commerce.framework.filter.RequestVariablesFilter;

/**
 * Filter配置
 *
 * @author Chenmm
 */
@Configuration
public class RequestFilterConfig {
	
	/**
     * 用于在RequestVariablesFilter中排除过虑
     */
    private String startWith = "/rms";

    public String getStartWith() {
		return startWith;
	}

	public void setStartWith(String startWith) {
		this.startWith = startWith;
	}

	@Bean
    public FilterRegistrationBean<RequestVariablesFilter> requestVariablesFilterRegistration() {
        FilterRegistrationBean<RequestVariablesFilter> registration = new FilterRegistrationBean<RequestVariablesFilter>();
        registration.setFilter(new RequestVariablesFilter());
        registration.setOrder(Integer.MAX_VALUE - 2);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("startWith", startWith);
        return registration;
    }
}
