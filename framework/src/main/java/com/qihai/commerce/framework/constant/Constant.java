package com.qihai.commerce.framework.constant;

/**
 * 公共常量类
 * 
 * @author zhugj
 * @date 2018年5月18日 下午2:35:06
 * @version 1.0.0 
 */
public final class Constant {
	/**
	 * 删除标识符 1 删除 0没有删除
	 */
	//删除标记（0：正常；1：删除；）
	public static final String DELTE_FLAG = "delFlag";
	public static final String DELTE_FLAG_YES = "1";
	public static final String DELTE_FLAG_NO = "0";
	
	/**
	 * 禁用启用标识符 1禁用 0启用
	 */
	public static final String ACT_FLAG = "actFlag";
	public static final String ACT_FLAG_YES = "0";
	public static final String ACT_FLAG_NO = "1";
	
	/**
	 * 是否显示标记（1显示0掩藏）
	 */
	public static final String MENU_ISSHOW = "isShow";
	public static final String MENU_SHOW = "1";
	public static final String MENU_HIDE = "0";
    
	/**
	 * 性别(男:male,女:female)
	 */
	public static final String GENDER_MALE="male";
	public static final String GENDER_FEMALE="female";
	
	/**错误代码*/
	public static final String CODE = "code";
	/**错误信息*/
	public static final String MSG = "msg";
	/**错误代码*/
	public static final String SUCCESS = "success";
	/**错误代码*/
	public static final String ERROR_CODE = "error_code";
	/**错误代码*/
	public static final String ERROR_MSG = "error_msg";
	/**默认日期格式*/
	public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**响应内容类型*/
	public static final String RESPONSE_CONTENT_TYPE = "application/json;charset=utf-8";

	/**当前用户的所有授权功能(set<功能路径>)*/
	public static final String PERSONAL_PERMISSION = "personPermission";
	/**当前用户的所有授权功能(String","拼接起来)*/
	public static final String PERSONAL_PERMISSION_STRING = "personPermissionString";
	/**当前用户的所有授权功能(Map<ID,功能路径>)*/
	public static final String PERSONAL_PERMISSION_MAP = "personPermissionMap";
	/**系统所有功能*/
	public static final String SOURCE_PERMISSION = "sourcePermission";
	/**系统登录用户id*/
	public static final String SYSTEM_LOGIN_USER_ID = "system_login_user_id";
	/** 系统登录用户*/
	public static final String SYSTEM_LOGIN_USER = "system_login_user_";
	/** 视图状态名称 */
	public static final String VIEW_STATE_NAME = "_VIEW_STATE";
	
	/**超级管理员ID*/
	public static final String ADMIN_ID = "1";
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
	
	/** 页面返回结果状态 */
	public static final String RESULT_SCS = "success";
	public static final String RESULT_ERR = "error";
	public static final String RESULT_EXC = "exception";
	public static final String RESULT_VALID = "validates";
	public static final String RESULT_VALID_STR = "validate";
	public static final String RESULT_VALID_MSG = "输入数据验证失败！";
	public static final String RESULT_SCS_MSG = "操作成功！";
	public static final String RESULT_EXC_MSG = "操作异常！";
	public static final String RESULT_ERR_MSG = "操作错误！";
	public static final String RESULT_DEL_MSG = "删除成功！";
	
	//线程数
    public final static int THREAD_COUNT = 5;

    //处理间隔时间
    //mils
    public final static int MQ_INTERVAL_MILS = 0;

    //consumer失败后等待时间(mils)
    public static final int MQ_ONE_SECOND = 1 * 1000;

    //异常sleep时间(mils)
    public static final int MQ_ONE_MINUTE = 1 * 60 * 1000;
    
    //MQ消息retry时间
    public static final int MQ_RETRY_TIME_INTERVAL = MQ_ONE_MINUTE;
    
    //MQ消息有效时间
    public static final int MQ_VALID_TIME = MQ_ONE_MINUTE;
}
