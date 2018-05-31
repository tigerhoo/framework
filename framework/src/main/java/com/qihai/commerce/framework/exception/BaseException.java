package com.qihai.commerce.framework.exception;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION : 基本运行时异常
 * @AUTHOR : zhu.gj
 * @DATE :2017-9-07
 *       </p>
 **************************************************************** 
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1325426932819373813L;

	private String msg;
    private int code = 1500;
    
    public BaseException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BaseException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BaseException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public BaseException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
