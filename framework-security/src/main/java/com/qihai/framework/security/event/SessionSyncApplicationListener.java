package com.qihai.framework.security.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.qihai.framework.security.cache.SessionLocalCache;

/**
 * Created by liheng on 2017/6/10.
 */
@Component
public class SessionSyncApplicationListener implements ApplicationListener<SessionSyncApplicationEvent> {
    @Async
    @Override
    public void onApplicationEvent(SessionSyncApplicationEvent event) {
    	SessionLocalCache.addProfileId(event.getSid());
    }
}