package com.qihai.commerce.framework.utils.xss;

import org.apache.commons.lang.StringUtils;

import com.qihai.commerce.framework.exception.BaseException;

/**
 * SQL过滤
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new BaseException("包含非法字符");
            }
        }

        return str;
    }
}
