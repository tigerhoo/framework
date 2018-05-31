package com.qihai.commerce.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qihai.commerce.framework.utils.ShiroUtils;

/**
 * Controller公共组件
 * 
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String getUserName() {
		return ShiroUtils.getUserName();
	}

}
