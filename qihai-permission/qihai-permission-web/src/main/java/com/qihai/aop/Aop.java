package com.qihai.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.qihai.commerce.framework.utils.ContextUtils;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.permission.entity.common.CommonEntity;

@Aspect
@Configuration
public class Aop {

	private static final Logger logger = LoggerFactory.getLogger(Aop.class);

	UserInfo userInfo = ContextUtils.getUserInfo();

	private String createdBy = "123";
	private String updatedBy = "123";

	/**
	 * 保存实体到表前，添加创建人和更新人.
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around(value = "execution(* com.qihai.permission..*.insert(..))")
	public Object saveEntityAop(ProceedingJoinPoint joinPoint) {
		Object rtnObj = "";
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			Object object = args[0];
			if (object instanceof CommonEntity) {
				// ((CommonEntity<?>) object).setCreatedBy(userInfo.getUsername());
				// ((CommonEntity<?>) object).setUpdatedBy(userInfo.getUsername());
				((CommonEntity<?>) object).setUpdatedBy(createdBy);
				((CommonEntity<?>) object).setUpdatedBy(updatedBy);
			}
		}
		try {
			rtnObj = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("切面异常", e);
		}
		return rtnObj;
	}

	/**
	 * 更新实体到表时，修改最后更新人.
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around(value = "execution(* com.qihai.permission..*.update*(..))")
	public Object updateEntityAop(ProceedingJoinPoint joinPoint) {
		Object rtnObj = "";
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			Object object = args[0];
			if (object instanceof CommonEntity) {
				// ((CommonEntity<?>) object).setUpdatedBy(userInfo.getUsername());
				((CommonEntity<?>) object).setUpdatedBy(updatedBy);
			}
		}
		try {
			rtnObj = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("切面异常", e);
		}
		return rtnObj;
	}

}
