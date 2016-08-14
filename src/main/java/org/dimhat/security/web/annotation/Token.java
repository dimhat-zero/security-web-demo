package org.dimhat.security.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防csrf token，同时也防表单重复提交
 * 用于post表单
 * 
 * @author dimhat
 * @date 2016年8月12日 下午2:08:49
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

	boolean create() default false;

	boolean remove() default false;

}
