package org.dimhat.security.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要权限，可注解在方法和类上
 * 如果注解在方法上则类上的被覆盖
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePerm {
    String[] value() default {};
    Logical logical() default Logical.OR;
}
