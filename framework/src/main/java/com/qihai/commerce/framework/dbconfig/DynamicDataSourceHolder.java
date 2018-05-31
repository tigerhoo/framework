package com.qihai.commerce.framework.dbconfig;


/**
 * 动态数据源处理
 * 
 * @author zhugj
 * @date 2018年01月04日 下午2:35:06
 * @version 1.0.0 
 */ 
public class DynamicDataSourceHolder {
	private static ThreadLocal<String> holderDataSource = new ThreadLocal<>();

	public static void setDataSource(String dataSource) {
		holderDataSource.set(dataSource);
	}

	public static String getDataSource() {
		return holderDataSource.get();
	}

	public static void clear() {
		holderDataSource.remove();
	}

}
