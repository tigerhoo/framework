package com.qihai.commerce.framework.utils;

/**
 * boolean工具类
 * 
 * @author wupengfei
 *
 */
public abstract class BooleanUtils {

	/**
	 * 判断参数是否为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(String... params) {
		if (params == null || params.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断参数是否为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(Object... params) {
		if (params == null || params.length == 0) {
			return true;
		}
		return false;
	}

}
