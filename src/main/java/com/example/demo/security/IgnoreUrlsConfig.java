package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weiqisheng
 * @Title: IgnoreUrlsConfig
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/229:57
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();
}
