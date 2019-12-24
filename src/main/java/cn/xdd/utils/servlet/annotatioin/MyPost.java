package cn.xdd.utils.servlet.annotatioin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@author: xchb
 *@date: 2019年12月23日下午6:13:16
 *@description: good good study,day day up
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyPost {
	String value();
	String type() default "POST";
}
