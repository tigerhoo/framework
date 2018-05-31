package com.qihai.commerce.framework.utils;

import org.springframework.core.NamedThreadLocal;

import com.qihai.commerce.framework.exception.BaseException;
import com.qihai.commerce.framework.vo.InvocationContext;
import com.qihai.commerce.framework.vo.ViewState;

/**
 * 单线程调用上下文容器
 * 
 * @author Chenmm
 */
public abstract class SingleThreadInvocationContextHolder {

	private static final ThreadLocal<InvocationContext> invocationContextHolder = new NamedThreadLocal<InvocationContext>("SingleThreadInvocationContext");

	/**
	 * 重置调用上下文
	 */
	public static void resetInvocationContext() {
		invocationContextHolder.set(null);
	}

	/**
	 * 设置调用上下文
	 * 
	 * @return InvocationContext
	 */
	public static void setInvocationContext(InvocationContext invocationContext) {
		invocationContextHolder.set(invocationContext);
	}

	/**
	 * 使用RPC调用上下文
	 */
	public static void useRpcInvocationContext() {
		try {
			resetViewState(ViewState.RPC_VS);
		} catch (BaseException ex) {
			setInvocationContext(new InvocationContext(ViewState.RPC_VS));
		}
	}
	
	/**
	 * 获取调用上下文
	 * 
	 * @return InvocationContext
	 */
	public static InvocationContext getInvocationContext() {
		return invocationContextHolder.get();
	}

	/**
	 * 切换调用上下文视图状态
	 * 
	 * @param viewState
	 * @return InvocationContext
	 */
	public static InvocationContext resetViewState(ViewState viewState) {
		InvocationContext origInvocationContext = getInvocationContext();
		if (origInvocationContext == null) {
			throw new BaseException("invocation.context.not.found");
		}
		InvocationContext destInvocationContext = new InvocationContext(viewState, origInvocationContext.getCookies(), origInvocationContext.getHeaders(), origInvocationContext.getIpAddress(), origInvocationContext.getBrowser(), origInvocationContext.getAttributes());
		setInvocationContext(destInvocationContext);
		return destInvocationContext;
	}
	
	/**
	 * 获取当前调用上下文视图状态（不存在报错）
	 * 
	 * @return UserViewState
	 */
	public final static ViewState getViewState() {
		InvocationContext invocationContext = invocationContextHolder.get();
		if (invocationContext == null || invocationContext.getViewState() == null) {
			throw new BaseException("view.state.not.found");
		}
		return invocationContext.getViewState();
	}

	/**
	 * 获取当前调用上下文视图状态（不存在返回空）
	 * 
	 * @return UserViewState
	 */
	public final static ViewState safeGetViewState() {
		InvocationContext invocationContext = invocationContextHolder.get();
		return invocationContext == null ? null : invocationContext.getViewState();
	}
}
