package com.qihai.commerce.framework.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qihai.commerce.framework.constant.Constant;
import com.qihai.commerce.framework.utils.R;

/**
 * 异常处理器
 *
 * @author Mark zhuguojin@qihaiyun.com
 * @since 1.0.0 2016-10-27
 */
@RestControllerAdvice
public class BaseExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BaseException.class)
	public R handleBaseException(BaseException e){
		R r = new R();
		r.setCode(e.getCode());
		r.setMsg(e.getMessage());

		return r;
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(Constant.ERROR_CODE_DATABASE_EXIST);
		r.setMsg(Constant.ERROR_CODE_DATABASE_EXIST_MSG);
		return r;
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(Constant.ERROR_CODE_HAVE_NO_RIGHT);
		r.setMsg(Constant.ERROR_CODE_HAVE_NO_RIGHT_MSG);
		return r;
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		R r = new R();
		r.setCode(Constant.ERROR_CODE_UNKNOWN_EXCEPTION);
		r.setMsg(Constant.ERROR_CODE_UNKNOWN_EXCEPTION_MSG);
		return r;
	}
}
