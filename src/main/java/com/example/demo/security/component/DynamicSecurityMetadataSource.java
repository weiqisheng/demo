package com.example.demo.security.component;

import cn.hutool.core.util.URLUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UmsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weiqisheng
 * @Title: DynamicSecurityMetadataSource
 * @ProjectName demo
 * @Description: TODO 动态权限数据源，用于获取动态权限规则
 * @date 2020/12/2215:58
 */

public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{


    private static Map<String,ConfigAttribute> configAttributeMap = new HashMap<>();
//
    @Autowired
    private DynamicSecurityService dynamicSecurityService;


    @PostConstruct
    public void loadDataSource() {
       configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null){
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();

        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
