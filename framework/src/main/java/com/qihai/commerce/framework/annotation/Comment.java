package com.qihai.commerce.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 注释
 * @author zhugj
 * @date 2017年8月28日 下午2:35:06
 * @version 1.0.0 
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {
  String value() default "";
}
