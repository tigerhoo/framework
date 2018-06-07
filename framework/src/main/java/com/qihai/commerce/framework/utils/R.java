package com.qihai.commerce.framework.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 返回数据
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
public class R<T> implements Serializable {

	private static final long serialVersionUID = -6886816416762519601L;

	public static final String OK_CODE = "0";

	private String code;

	private String msg;

	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public R<T> ok(T data) {
		this.code = OK_CODE;
		this.data = data;

		return this;
	}

	public R<T> error(String code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
