package com.qihai.framework.security.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liheng on 2017/6/2.
 */
public interface Const {
    public final static String REQUEST_HEADER_KEY_TOKEN = "token";
    public final static String REQUEST_HEADER_KEY_SID = "sid";
    public final static String REQUEST_HEADER_KEY_REFRESH_TOKEN = "refresh_token";
    public final static String REQUEST_HEADER_KEY_DEVICE = "x-device";
    public final static String REQUEST_HEADER_KEY_VERSION = "x-version";

    public final static String REQUEST_HEADER_KEY_FROM = "x-from";

    public final static String REQUEST_HEADER_KEY_OPERATE="x-operate";

    public final static String CACHE_KEY_PREFIX_AUTH = "auth:";
    public final static String CACHE_KEY_PREFIX_PROFILE = "profile:";
    public final static String CACHE_KEY_EXPIRES_IN = "expire_time";
    public final static String CACHE_KEY_CHECK_SUM = "check_sum";

    public final static String CACHE_KEY_REFRESH_TOKEN = REQUEST_HEADER_KEY_REFRESH_TOKEN;
    
    public final static String ERROR_INVALID_TOKEN = "{\"code\": \"V10004\", \"msg\": \"无效的token！\", \"data\": {}}";
    public final static String ERROR_REFRESH_TOKEN_DISACCORD = "{\"code\": \"V10004\", \"msg\": \"refresh_token校验码失败！\", \"data\": {}}";
    public final static String ERROR_TOKEN_EXPIRED = "{\"code\": \"V10004\", \"msg\": \"token已过期！\", \"data\": {}}";
    
    public final static String URL_LOGIN = "/accessToken";
    public final static String URL_LOGIN_SMS = "/accessTokenSms";
    public final static String URL_USER_INFO = "/api/userInfo";
    public final static String URL_REFRESH_TOKEN = "/api/refreshToken";
    public final static String URL_LOGOUT = "/api/logout";
    public final static String URL_REFRESH = "/refresh";
    public final static String URL_CAPTCHA = "/captcha";
    public final static String URL_SMS_CAPTCHA = "/smsCaptcha";
    public final static String URL_ERROR = "/error";
    
    public final static List<String> URLS_LOCAL = Arrays.asList(URL_LOGIN, URL_LOGIN_SMS, URL_USER_INFO, URL_REFRESH_TOKEN, URL_LOGOUT, URL_REFRESH, URL_CAPTCHA, URL_SMS_CAPTCHA);

    //过滤访问地址
    public final static List<String> URLS_FILTER = Arrays.asList(URL_LOGIN, URL_LOGIN_SMS, URL_REFRESH, URL_SMS_CAPTCHA, URL_CAPTCHA, URL_ERROR);

}
