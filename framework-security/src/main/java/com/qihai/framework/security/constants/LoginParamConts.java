package com.qihai.framework.security.constants;

/**
 * Created by ligang on 2017/10/14 16:01
 */
public class LoginParamConts {

    public final static String PHONE_NUMBER = "phonenumber";//手机号

    public final static String SMS_CAPTCHA = "smscaptcha";//手机验证码

    public final static String USERNAME = "username";//用户名

    public final static String PASSWORD = "password";//密码

    public final static String X_LOGINTYPE = "logintype";//登录类型，版本号，1:官网，2：ios， 3：微信 4：安卓，5：微官网，7：PC客户端8：电商商户后台，9：电商运营管理后台

    public final static String X_DEVICECODE = "devicecode";//设备号  APP登录必输

    public final static String X_VERSION = "version";//APP版本号 APP登录必输

    public final static String X_FROM = "from";

    public final static String X_TYPE = "type";

    public final static String X_TYPE_C = "C";

    public final static String X_TYPE_B = "B";

    public final static String X_TYPE_S = "S";

    public final static String CAPTCHA = "captcha";//图像验证码

    public final static String TOKEN = "token";
}
