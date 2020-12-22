package com.example.demo.controller;

import com.example.demo.common.ResultObject;
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
    public ResultObject test(){
        return ResultObject.success("api123");
    }

    @GetMapping("/test1")
    public ResultObject test1(){
        return ResultObject.success("apia123");
    }

    @GetMapping("/test2")
    public ResultObject test2(){
        return ResultObject.success("apid123");
    }

    @GetMapping("/test3")
    public ResultObject test3(){
        return ResultObject.success("apif123");
    }

}
