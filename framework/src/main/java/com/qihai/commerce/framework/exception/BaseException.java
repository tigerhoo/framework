package com.qihai.commerce.framework.exception;

import com.qihai.commerce.framework.enums.BizErrorCode;

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

	private static final long serialVersionUID = 1325426932819373813L;

	//错误信息
	private String errorMsg;
	//错误代码
    private String errorCode = BizErrorCode.SysErrorType.SYSTEM_INNER_ERROR.getCode();
    
    public BaseException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public BaseException(String errorMsg, Throwable e) {
		super(errorMsg, e);
		this.errorMsg = errorMsg;
	}
	
	public BaseException(String errorMsg, String errorCode) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}
	
	public BaseException(String errorMsg, String errorCode, Throwable e) {
		super(errorMsg, e);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
