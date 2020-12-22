package com.example.demo.controller;

import com.example.demo.common.ResultObject;
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
    public ResultObject test(){
        return ResultObject.success("12312331");
    }
}
