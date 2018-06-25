package com.qihai.framework.security.config;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.security.web.CallbackFilter;
import org.pac4j.springframework.security.web.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.custom.MyPac4jEntryPoint;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class JwtWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Value("${auth.filter.apiUrl}")
        private String apiUrl;

        @Autowired
        private Config config;

        protected void configure(final HttpSecurity http) throws Exception {
            final SecurityFilter filter = new SecurityFilter(config, "HeaderClient", "custom");
            http
                    .requestMatchers()
                    .antMatchers(split(apiUrl))
                    .and()
                    .addFilterBefore(filter, BasicAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable().cors();
        }
    }

    @Configuration
    @Order(3)
    public static class DefaultWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private Config config;

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/css/**", "/js/**", "/scripts/**", "/img/**", "/fonts/**", "/lib/**", "/favicon.ico");
        }

        protected void configure(final HttpSecurity http) throws Exception {
            final CallbackFilter callbackFilter = new CallbackFilter(config);
            List<String> urls = new ArrayList<>(Const.URLS_FILTER);
            urls.add(0,"/");
            http
                    .authorizeRequests()
                    .antMatchers(urls.toArray(new String[urls.size()])).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(new MyPac4jEntryPoint(config, "DirectFormClient"))
                    .and()
                    .addFilterBefore(callbackFilter, BasicAuthenticationFilter.class)
                    .csrf().disable()
                    .logout()
                    .logoutUrl("/api/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/").permitAll();
        }
    }
    private static String[] split(String input) {
        String[] result = StringUtils.tokenizeToStringArray(input, ",");
        if (result == null) {
            result = new String[0];
        }
        final String xx = "**";
        //配置信息配置的地址为 /api/,/portal/,需要在后面增加**,表示此路径下的所有地址
        for (int i = 0; i<result.length; i++){
            if(!result[i].endsWith(xx)) {
                result[i] = result[i] + xx;
            }
        }
        return result;
    }
}