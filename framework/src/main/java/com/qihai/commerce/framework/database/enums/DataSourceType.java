package com.qihai.commerce.framework.database.enums;

/**
 * 主从数据库类型<br>
 * 
 * @author zhugj
 * @date 2017-12-26 上午10:26:41
 * @version 1.0.0
 */
public enum DataSourceType {

	read("read", "从库"), write("write", "主库");

	private String type;

	private String name;

	private DataSourceType(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
