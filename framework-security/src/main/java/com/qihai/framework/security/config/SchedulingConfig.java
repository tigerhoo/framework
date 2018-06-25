package com.qihai.framework.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.qihai.framework.security.cache.SessionLocalCache;
import com.qihai.framework.security.utils.CacheUtils;

/**
 * Created by liheng on 2017/6/10.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    private static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Value("${webuser.session.timeout}")
    private long sessionTimeout;

    @Scheduled(cron = "${scheduled.sync.session.cron}")
    public void scheduler() {
        CacheUtils.expireBatch(SessionLocalCache.getProfileIds(), sessionTimeout);
    }
}