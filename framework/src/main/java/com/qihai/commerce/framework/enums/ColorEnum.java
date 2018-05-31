package com.qihai.commerce.framework.enums;

/**
 * 颜色枚举
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-08-17 0:00
 * @version 1.0.0
 */
public enum ColorEnum {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ColorEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ColorEnum c : ColorEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
