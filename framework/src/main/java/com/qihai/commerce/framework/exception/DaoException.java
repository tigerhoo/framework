package com.qihai.commerce.framework.exception;

/**
 * Dao异常
 * 
 * @author zhugj
 * @date 2017年8月17日 下午2:35:06
 * @version 1.0.0 
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 3744880846740433336L;
	
	//错误编码
	private String errorCode;
	
	public DaoException() {
		super();
	}
	
	public DaoException(Throwable cause) {
		super(cause);
	}
	
	public DaoException(String message) {
		super(message);
    }
	
	public DaoException(String errorCode, String message, Throwable cause) {
        this(message, cause);
        this.errorCode = errorCode;
    }

    public DaoException(String errorCode, String message) {
        this(message);
        this.errorCode = errorCode;
    }

    public DaoException(String message, Throwable cause) {
    	 super(message, cause);
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
	
}
