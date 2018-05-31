package com.qihai.commerce.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIdGenerateUtils extends Thread {
	private static long orderNum = 0l;
	private static String date;
	private String name; 
	
	public OrderIdGenerateUtils(String name) {  
	       this.name=name;  
	    }  

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo() {
		String currentDateStr = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		if (date == null || date.compareTo(currentDateStr) < 0) {
			date = currentDateStr;
			orderNum = 0l;
		} 
		orderNum++;
		long orderNo = Long.parseLong((date)) * 1000000;
		orderNo += orderNum;
		;
		return orderNo + "";
	}
	
	public void run() {
		
        for (int i = 0; i < 1000; i++) {  
            //System.out.println(Thread.currentThread().getName() + "运行  :  " + i);  
            try {  
            	System.out.println(Thread.currentThread().getName() + "运行  :  " + OrderIdGenerateUtils.getOrderNo());  
            	//Thread.sleep(1000);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
         
    }  

	public static void main(String[] args) throws InterruptedException {
		OrderIdGenerateUtils mTh1=new OrderIdGenerateUtils("A");  
		OrderIdGenerateUtils mTh2=new OrderIdGenerateUtils("B");  
        mTh1.start();  
        mTh2.start();
	}

}
