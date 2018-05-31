package com.qihai.commerce.framework.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-06-08 0:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
