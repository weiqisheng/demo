package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author weiqisheng
 * @Title: TestProperties
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/2816:32
 */
@Component
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:test.yml")
public class TestProperties {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
