package com.qihai.commerce.framework.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * api接口返回结果通用类<br>
 * @author zhugj
 * @date 2017-8-17 上午10:26:41
 * @version 1.0.0 
 */
public class ResultDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String OK_CODE = "0";
	
	//运行时出错异常代码
	public static final String ERROR_CODE = "-1";
	
	//业务异常代码
	public static final String BUSINESS_ERROR_CODE = "-2";

	/**
	 * 返回代码code=0成功其余失败
	 */
	private String code;
	
	/**
	 * 返回消息
	 */
	private String message;
	
	/**
	 * 返回数据对象
	 */
	private Object data;
	
	public ResultDto() {
		super();
	}
	
	public ResultDto(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
