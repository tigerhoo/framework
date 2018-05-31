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
	
	private int code;
	
	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(Throwable cause) {
		super(cause);
	}
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DaoException(int code) {
        super();
        this.code = code;
    }
	
	public DaoException(int code, String message, Throwable cause) {
        this(message, cause);
        this.code = code;
    }

    public DaoException(int code, String message) {
        this(message);
        this.code = code;
    }

    public DaoException(int code, Throwable cause) {
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
