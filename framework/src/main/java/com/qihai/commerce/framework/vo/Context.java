package com.qihai.commerce.framework.vo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liheng
 * @since 1.0
 */
public class Context {
    private String userId;
    private String url;
    private String ipAddress;

    private UserInfo user;


    private ConcurrentHashMap<String, Object>  globalVariableMap = new ConcurrentHashMap<>();

    public String getUserId() {
        return userId;
    }

    public UserInfo getUser() {
        if (user == null) {
            user = new UserInfo();
        }
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
        if (this.user != null) {
            this.userId = this.user.getId();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ConcurrentHashMap<String, Object> getGlobalVariableMap() {
        return globalVariableMap;
    }

    public void setGlobalVariableMap(ConcurrentHashMap<String, Object> globalVariableMap) {
        this.globalVariableMap = globalVariableMap;
    }

    public void addGlobalVariable(String key, Object value) {
        globalVariableMap.put(key, value);
    }

    public Object getGlobalVariable(String key) {
        return globalVariableMap.get(key);
    }
}