package com.qihai.commerce.framework.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 订单生成工具类
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2018-01-05 0:00
 */
public class GenerateOrderNoUtils {

	private final static Logger logger = LoggerFactory.getLogger(GenerateOrderNoUtils.class);

	private static long orderNum = 0l;

	private static String date;

	private static final Long MAX_ORDER_NUM = 100000000L;

	private static String serviceId;

	static {
		Properties prop = new Properties();
		ClassLoader loader = GenerateOrderNoUtils.class.getClassLoader();

		try {
			prop.load(loader.getResourceAsStream("application.properties"));
			serviceId = prop.getProperty("service.id");
		} catch (IOException e) {
			logger.error("读取属性文件失败", e);
		}
	}

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo() {
		String currentDateStr = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		if (date == null || date.compareTo(currentDateStr) < 0) { //如果当前时间大于date则订单号从0开始,如果当前时间小于等于date,则继续根据date进行继续增长
			date = currentDateStr;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date)) * MAX_ORDER_NUM;
		orderNo += orderNum;
		return serviceId + orderNo;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			System.out.println(GenerateOrderNoUtils.getOrderNo());
			Thread.sleep(1000);
		}
	}

}
