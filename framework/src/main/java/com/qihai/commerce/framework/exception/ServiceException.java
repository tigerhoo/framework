package com.qihai.commerce.framework.exception;

/**
 * Service异常
 * 
 * @author zhugj
 * @date 2017年8月17日 下午2:35:06
 * @version 1.0.0 
 */
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -8396020297026661487L;
	
	//错误编码
	private String errorCode;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message) {
		super(message);
    }
	
	public ServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, String message) {
    	this(message);
		this.errorCode = errorCode;
    }

    public ServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
	
}
