package com.qihai.commerce.framework.utils;

import com.qihai.commerce.framework.vo.Context;
import com.qihai.commerce.framework.vo.UserInfo;

/**
 * @author liheng
 * @since 1.0
 */
public class ContextUtils {
    private static ThreadLocal<Context> currentLocalContext = new InheritableThreadLocal<>();


    public static Context get() {
        return currentLocalContext.get();
    }

    public static void set(Context context) {
        currentLocalContext.set(context);
    }

    public static void unset() {
        currentLocalContext.remove();
    }
    
    public static UserInfo getUserInfo() {
    	UserInfo userInfo = null;
    	Context context = get();
    	if (null != context) {
    		userInfo = context.getUser();
		}
        return userInfo;
    }

    public static void addGlobalVariable(String key, Object value) {
        Context context = get();
        if (context == null) {
            set(new Context());
            context = get();
        }

        context.addGlobalVariable(key, value);
    }

    public Object getGlobalVariable(String key) {
        Object result = null;

        Context context = get();
        if (context != null) {
            result = context.getGlobalVariable(key);
        }

        return result;
    }

}
