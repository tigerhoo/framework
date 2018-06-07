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

	//错误编码
	private String errorCode;

	public RpcException() {
		super();
	}

	public RpcException(Throwable cause) {
		super(cause);
	}

	public RpcException(String message) {
		super(message);
	}

	public RpcException(String errorCode, String message, Throwable cause) {
		this(message, cause);
		this.errorCode = errorCode;
	}

	public RpcException(String errorCode, String message) {
		this(message);
		this.errorCode = errorCode;
	}

	public RpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
}
