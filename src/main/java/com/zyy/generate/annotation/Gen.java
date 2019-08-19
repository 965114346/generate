package com.zyy.generate.annotation;

import java.lang.annotation.*;

/**
 * 生成注解
 * @author yangyang.zhang
 * @date 2019年08月17日23:04:45
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gen {
    String value() default "";
}
