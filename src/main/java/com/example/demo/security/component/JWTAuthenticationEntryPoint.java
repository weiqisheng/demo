package com.example.demo.security.component;


import cn.hutool.json.JSONUtil;
import com.example.demo.common.CommonResult;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weiqisheng
 * @Title: JWTAuthenticationEntryPoint
 * @ProjectName myProject
 * @Description: TODO 自定义返回结果：未登录或登录过期
 * @date 2020/12/1116:26
 */
public class JWTAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
