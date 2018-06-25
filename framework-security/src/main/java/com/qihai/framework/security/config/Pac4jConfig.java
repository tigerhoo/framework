package com.qihai.framework.security.config;

import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.DirectFormClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.custom.MyUsernamePasswordAuthenticator;
import com.qihai.framework.security.filter.TokenFilter;

@Configuration
public class Pac4jConfig {

    @Value("${salt}")
    private String salt;

    @Bean
    public Config config() {
        SecretSignatureConfiguration secretSignatureConfiguration = new SecretSignatureConfiguration(salt);
        SecretEncryptionConfiguration secretEncryptionConfiguration = new SecretEncryptionConfiguration(salt);
        final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(secretSignatureConfiguration, secretEncryptionConfiguration);
        HeaderClient headerClient = new HeaderClient(Const.REQUEST_HEADER_KEY_TOKEN, jwtAuthenticator);

        final MyUsernamePasswordAuthenticator myUsernamePasswordAuthenticator = new MyUsernamePasswordAuthenticator();
        final DirectFormClient formClient = new DirectFormClient(myUsernamePasswordAuthenticator);

        //final Clients clients = new Clients(callbackUrl, formClient, headerClient);
        final Clients clients = new Clients(formClient, headerClient);

        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer<>("ROLE_ADMIN"));
        config.addAuthorizer("custom", new CustomAuthorizer());
        return config;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
    
    @Bean
    public FilterRegistrationBean<TokenFilter> filterRegistrationBean() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        TokenFilter httpBasicFilter = new TokenFilter();
        registrationBean.setFilter(httpBasicFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MIN_VALUE);
        return registrationBean;
    }
}
