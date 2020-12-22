package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiqisheng
 * @Title: ApiControllr
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/1411:08
 */
@RestController
@RequestMapping("/api")
public class ApiControllr {

    @GetMapping("/test")
    public CommonResult test(){
        return CommonResult.success("api123");
    }

}
