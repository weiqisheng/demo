package com.example.demo.config;

import com.example.demo.common.CommonResult;
import com.example.demo.model.AdminUserDetails;
import com.example.demo.utils.JwtTokenUtils;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author weiqisheng
 * @Title: SecurityHandlerConfig
 * @ProjectName demo
 * @Description: TODO  security处理器
 * @date 2020/12/2210:18
 */
@Configuration
public class SecurityHandlerConfig {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    /**
     * 验证用户名和密码成功后调用的方法，生成token
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                Object pricipal = null;
                if (!Objects.isNull(authentication)){
                    pricipal = authentication.getPrincipal();
                }
                if (!Objects.isNull(pricipal)){
                    AdminUserDetails adminUserDetails  = (AdminUserDetails)pricipal;
                    String token = jwtTokenUtils.generateToken(adminUserDetails);
                    Map<String,String> map  = new HashMap<>();
                    map.put("token",token);
                    map.put("header",tokenHead);
                    ResponseUtil.responseJson(httpServletResponse, CommonResult.success(map));
                }
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler(){
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException failed) throws IOException, ServletException {
                String returenData = "";
                if (failed instanceof AccountExpiredException){
                    returenData="账号过期";
                }
                else if (failed instanceof BadCredentialsException){
                    returenData="密码错误";
                }
                else if (failed instanceof CredentialsExpiredException){
                    returenData="密码过期";
                }else if (failed instanceof DisabledException){
                    returenData = "账号不可用";
                }else if (failed instanceof LockedException){
                    returenData = "账号锁定";
                }else if (failed instanceof InternalAuthenticationServiceException){
                    returenData = "用户不存在";
                }else {
                    returenData = "未知异常";
                }
                ResponseUtil.responseJson(httpServletResponse, CommonResult.validateFailed(returenData));
            }
        };
    }

}
