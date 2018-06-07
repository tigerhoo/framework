package com.qihai.commerce.framework.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.utils.R;

/**
 * 异常处理器
 *
 * @author Mark zhuguojin@qihaiyun.com
 * @since 1.0.0 2018-5-27
 */
@RestControllerAdvice
public class BaseExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BaseException.class)
	public R handleBaseException(BaseException e) {
		R r = new R();
		r.setCode(e.getErrorCode());
		r.setMsg(e.getMessage());

		return r;
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e) {
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(BizErrorCode.DBErrorType.DATA_ALREADY_EXISTED.getCode());
		r.setMsg(BizErrorCode.DBErrorType.DATA_ALREADY_EXISTED.getDesc());
		return r;
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e) {
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(BizErrorCode.PermissionErrorType.PERMISSION_NO_ACCESS.getCode());
		r.setMsg(BizErrorCode.PermissionErrorType.PERMISSION_NO_ACCESS.getDesc());
		return r;
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e) {
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(BizErrorCode.SysErrorType.SYSTEM_INNER_ERROR.getCode());
		r.setMsg(BizErrorCode.SysErrorType.SYSTEM_INNER_ERROR.getDesc());
		return r;
	}
}
