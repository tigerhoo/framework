package com.qihai.commerce.framework.exception;

/**
 * Rpc调用异常
 * 
 * @author zhugj
 * @date 2017年8月17日 下午2:35:06
 * @version 1.0.0 
 */
public class RpcException extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;

	public RpcException() {
		super();
	}

	public RpcException(String message) {
		super(message);
	}

	public RpcException(Throwable cause) {
		super(cause);
	}

	public RpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public RpcException(int code) {
		super();
		this.code = code;
	}

	public RpcException(int code, String message, Throwable cause) {
		this(message, cause);
		this.code = code;
	}

	public RpcException(int code, String message) {
		this(message);
		this.code = code;
	}

	public RpcException(int code, Throwable cause) {
		this(cause);
		this.code = code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
