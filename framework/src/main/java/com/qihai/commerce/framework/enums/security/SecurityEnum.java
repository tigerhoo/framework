package com.qihai.commerce.framework.enums.security;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全
 * 
 * @author wupengfei
 *
 */
public enum SecurityEnum {

	SecurityEnum;

	public enum Status {

		/**
		 * 未用
		 */
		unused(0),

		/**
		 * 已用
		 */
		used(1);

		private int value;

		private Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum Type {

		/**
		 * 订单
		 */
		order(0);

		private int value;

		private static Map<Integer, Type> typeMap = new HashMap<Integer, Type>();

		static {
			Type[] typeEnums = Type.values();
			for (Type type : typeEnums) {
				typeMap.put(type.getValue(), type);
			}
		}

		private Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		/**
		 * get type
		 * 
		 * @param value
		 * @return
		 */
		public static Type getType(int value) {
			return typeMap.get(value);
		}
	}
}
