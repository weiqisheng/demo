package com.example.demo.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author weiqisheng
 * @Title: DynamicSecurityService
 * @ProjectName demo
 * @Description: TODO 动态权限相关业务类
 * @date 2020/12/2215:30
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
