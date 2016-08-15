package org.dimhat.security.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在参数上标注是否注入用户信息
 * 
 * @author dimhat
 * @date 2016年8月13日 上午10:30:06
 * @version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {

	boolean required() default true;
}
