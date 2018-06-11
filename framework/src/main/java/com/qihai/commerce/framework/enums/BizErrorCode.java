package com.qihai.commerce.framework.enums;

/**
 * 业务异常枚举
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2018-05-28 0:00
 * @version 1.0.0
 */
public class BizErrorCode {
	/**
	 * 成功失败枚举
	 * 
	 */
	public enum ReturnType {
		IS_SUCCESS("0","成功"),
		IS_FAIL ("-1","失败");

	    private String code;
	    private String desc;

	    private ReturnType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 验证枚举类型
	 * 
	 */
	public enum ValidateErrorType {
		PARAMS_IS_NULL("V10001","参数为空"),
		PARAMS_NOT_COMPLETE("V10002","参数不全"),
		PARAMS_TYPE_ERROR("V10003","参数类型错误"),
		PARAMS_IS_INVALID("V10004","参数无效");

	    private String code;
	    private String desc;

	    private ValidateErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 验证枚举类型
	 * 
	 */
	public enum UserErrorType {
		USER_NOT_EXIST("U20001","用户不存在"),
		USER_NOT_LOGGED_IN ("U20002","用户未登陆"),
		USER_ACCOUNT_ERROR ("U20003","用户名或密码错误"),
		USER_ACCOUNT_FORBIDDEN  ("U20004","用户账户已被禁用"),
		USER_HAS_EXIST ("U20005","用户已存在");

	    private String code;
	    private String desc;

	    private UserErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 业务枚举类型
	 * 
	 */
	public enum BizErrorType {
		BUSINESS_ERROR("B30001","系统业务出现问题");

		private String code;
	    private String desc;

	    private BizErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }
	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 系统枚举类型
	 * 
	 */
	public enum SysErrorType {
		SYSTEM_INNER_ERROR("S40001","系统内部错误");

		private String code;
	    private String desc;

	    private SysErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }
	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	
	/**
	 * DB枚举类型
	 * 
	 */
	public enum DBErrorType {
		DATA_NOT_FOUND("DB50001","数据未找到"),
		DATA_IS_WRONG("DB50002","数据有误"),
		DATA_ALREADY_EXISTED("DB50003","数据已存在"),
		DATABASE_NOT_EXISTED("DB50004","数据库不存在"),
		DATATABLE_ALREADY_EXISTED("DB50005","数据表已存在"),
		DATATABLE_NOT_EXISTED("DB50006","数据表不存在"),
		DATATABLE_FIELD_NOT_EXISTED("DB50007","字段不存在"),
		DATATABLE_RECORD_NOT_EXISTED("DB50008","记录不存在"),
		DATATABLE_FIELD_REPEAT_IN_STORAGE("DB50009","字段值重复，入库失败"),
		DATATABLE_FIELD_REPEAT_TO_UPDATE_RECORD("DB50010","字段值重复，更新记录失败"),
		DATA_SQL_INVALID("DB50011","无效的SQL语句");

	    private String code;
	    private String desc;

	    private DBErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 接口枚举类型
	 * 
	 */
	public enum ApiErrorType {
		INTERFACE_INNER_INVOKE_ERROR("I60001","系统内部接口调用异常"),
		INTERFACE_OUTER_INVOKE_ERROR("I60002","系统外部接口调用异常"),
		INTERFACE_FORBIDDEN("I60003","接口禁止访问"),
		INTERFACE_ADDRESS_INVALID("I60004","接口地址无效"),
		INTERFACE_REQUEST_TIMEOUT("I60005","接口请求超时"),
		INTERFACE_EXCEED_LOAD("I60006","接口负载过高"),
		INTERFACE_SERVICE_NOT_AVAILABLE("I60007","服务暂不可用"),
		INTERFACE_CALL_TIMES_EXCEED_LIMIT("I60008","接口调用次数已达到设定的上限");

	    private String code;
	    private String desc;

	    private ApiErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	/**
	 * 权限枚举类型
	 * 
	 */
	public enum PermissionErrorType {
		PERMISSION_NO_ACCESS("P70001","没有访问权限");

	    private String code;
	    private String desc;

	    private PermissionErrorType(String code, String desc) {
	        this.setCode(code);
	        this.setDesc(desc);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.code + "]" + this.desc;
	    }
	}
	
}
