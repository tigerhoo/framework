package com.qihai.commerce.framework.utils;

/**
 * 返回数据
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
public class R<T> {
	
	private int code;
	
	private String msg;
	
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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
		this.code = 0;
		this.data = data; 
		
		return this;
	}
	
	public R<T> error(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}
}
