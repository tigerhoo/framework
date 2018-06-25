package com.qihai.framework.security.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by liheng on 2017/6/10.
 */
public class SessionSyncApplicationEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private String sid;

    public SessionSyncApplicationEvent(Object source, String sid) {
        super(source);
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
