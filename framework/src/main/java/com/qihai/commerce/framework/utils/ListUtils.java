package com.qihai.commerce.framework.utils;

import java.util.List;

/**
 * 集合工具类
 * 
 * @author wupengfei
 *
 */
public abstract class ListUtils {

	/**
	 * 把list转为string
	 * 
	 * @param list
	 * @return
	 */
	public static String toString(List<String> list) {
		StringBuilder result = new StringBuilder();
		int size = (list != null) ? list.size() : 0;
		for (int i = 0; i < size; i++) {
			result.append(list.get(i));
		}
		return result.toString();
	}
}
