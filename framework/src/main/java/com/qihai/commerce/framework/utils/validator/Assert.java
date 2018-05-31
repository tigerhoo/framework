package com.qihai.commerce.framework.utils.validator;

import org.apache.commons.lang.StringUtils;

import com.qihai.commerce.framework.exception.BaseException;

/**
 * 数据校验
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-09-29 0:00
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BaseException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BaseException(message);
        }
    }
}
