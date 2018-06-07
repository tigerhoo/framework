package com.qihai.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class PermissionAop {

//	@Before("execution(public * com.qihai.permission.controller.*save(..))||execution(public * com.qihai.permission.controller.*.update(..))")
//	public void before(JoinPoint joinPoint) throws Throwable {
//		System.out.println("aaa");
//	}
	
//	@Around(value="bean(*Controller)")
//	public void around(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println();
//		Object[] args = pjp.getArgs();
//		if(args.length>0) {
//			
//		}
//		System.out.println(args);
//		pjp.proceed();
//	}
	
}
