package com.example.demo.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weiqisheng
 * @Title: VerifyCodeUtil
 * @ProjectName utils
 * @Description: TODO  获取验证码
 * @date 2020/12/2510:53
 */
public class VerifyCodeUtil {


    /**
     * LineCaptcha 线段干扰的验证码
     * @param response
     */
    public static String lineCatcha(HttpServletResponse response){
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(80,30,4,10);
        String verifyCode = lineCaptcha.getCode();
        //设置响应头
        response.setHeader("Pragma","no-cache");
        //设置响应头
        response.setHeader("Cache-Control","no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires",0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

    /**
     * CircleCaptcha 圆圈干扰验证码
     * @param response
     */
    public static String circleCaptcha(HttpServletResponse response){
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(80,30,4,10);
        String verifyCode = circleCaptcha.getCode();
        //设置响应头
        response.setHeader("Pragma","no-cache");
        //设置响应头
        response.setHeader("Cache-Control","no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires",0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            circleCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

    /**
     * ShearCaptcha 扭曲干扰验证码
     * @param response
     */
    public static String shearCaptcha(HttpServletResponse response){
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(80,30,4,4 );
        String verifyCode = shearCaptcha.getCode();
        //设置响应头
        response.setHeader("Pragma","no-cache");
        //设置响应头
        response.setHeader("Cache-Control","no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires",0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            shearCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }
}
