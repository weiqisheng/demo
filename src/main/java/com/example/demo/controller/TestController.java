package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiqisheng
 * @Title: TestController
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1117:01
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public CommonResult test(){
        return CommonResult.success("12312331");
    }
}
