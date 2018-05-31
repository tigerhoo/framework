package com.qihai.commerce.framework.utils;

import java.math.BigDecimal;

/**
 * bigdecimal工具类
 * 
 * @author wupengfei
 * 
 */
public abstract class BigDecimalUtils {

	private static int SCALE_NUM = 2;

	/**
	 * 格式化到2位精度
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal format(BigDecimal value) {
		return format(value, SCALE_NUM);
	}

	/**
	 * 格式化到scale位精度
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static BigDecimal format(BigDecimal value, int scale) {
		if (value == null) {
			return null;
		}
		return value.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 两个数相加并格式化到2位精度
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal add(Double param1, Double param2) {
		if (param1 == null && param2 == null) {
			return null;
		}

		if (param1 != null && param2 != null) {
			BigDecimal bd1 = new BigDecimal(param1);
			BigDecimal bd2 = new BigDecimal(param2);
			BigDecimal result = format(bd1.add(bd2));
			return result;
		}
		if (param1 == null) {
			return format(new BigDecimal(param2));
		}
		return format(new BigDecimal(param1));
	}

	/**
	 * 两个数相减，参数必须为非空，否则返回null
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal subtract(Double param1, Double param2) {
		if (param1 != null && param2 != null) {
			BigDecimal bd1 = new BigDecimal(param1);
			BigDecimal bd2 = new BigDecimal(param2);
			BigDecimal result = bd1.subtract(bd2);
			result = result.setScale(2,   BigDecimal.ROUND_HALF_UP);//保留2位小数 9.9-8.0 = 1.9000000000000004
			return result;
		}
		return null;
	}

	/**
	 * 两个数相乘，参数必须为非空，否则返回null
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal multiply(Double param1, Double param2) {
		if (param1 != null && param2 != null) {
			BigDecimal bd1 = new BigDecimal(param1);
			BigDecimal bd2 = new BigDecimal(param2);
			BigDecimal result = bd1.multiply(bd2);
			return result;
		}
		return null;
	}

}
