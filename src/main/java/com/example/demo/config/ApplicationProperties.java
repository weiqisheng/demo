package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weiqisheng
 * @Title: ApplicationProperties
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/2816:36
 */
@Component
@ConfigurationProperties(prefix = "oath")
public class ApplicationProperties {

    private String boy;

    private String action;

    private String girl;

    private String time;

    public String getBoy() {
        return boy;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getGirl() {
        return girl;
    }

    public void setGirl(String girl) {
        this.girl = girl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
