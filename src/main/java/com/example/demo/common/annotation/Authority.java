package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * @author weiqisheng
 * @Title: Authority
 * @ProjectName demo
 * @Description: TODO
 * @date 2021/4/279:45
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {

    String name();

    String pid();
}
