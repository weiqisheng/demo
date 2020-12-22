package com.example.demo.config;


import com.example.demo.common.constants.SecurityConstants;
import com.example.demo.jwt.JWTAuthenticationEntryPoint;
import com.example.demo.jwt.JWTAuthenticationFilter;
import com.example.demo.jwt.JwtAccessDeniedHandler;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

import static java.util.Collections.singletonList;

/**
 * @author weiqisheng
 * @Title: SecurityConfig
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1111:13
 */

@Configuration
@EnableWebSecurity  //开启security
@EnableGlobalMethodSecurity(prePostEnabled = true) //保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

//    //拦截配置
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //跨域共享
//        http.cors()
//                .and()
//                //禁用csrf
//                .csrf().disable()
//
//
//
//                //不创建会话
//
//
//
//                .authorizeRequests()
//                // 测试用资源，需要验证了的用户才能访问
//                .antMatchers("/tasks/**")
//                .authenticated()
//                .antMatchers(HttpMethod.DELETE, "/tasks/**")
//                .hasRole("ADMIN")
//                // 其他都放行了
//                .anyRequest().permitAll()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                //添加jwt鉴权拦截器
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new JWTAuthenticationEntryPoint());
//
//    }
//
//    /**
//     * 跨域配置
//     * @return 基于URL的跨域配置信息
//     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // 注册跨域配置
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }


    /**
     * 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                // 禁用 CSRF
                .csrf().disable()
                .authorizeRequests()
                // swagger
                .antMatchers(SecurityConstants.SWAGGER_WHITELIST).permitAll()
                .antMatchers("/test","/login.html").permitAll()
                // 登录接口
                .antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_WHITELIST).permitAll()
                // 指定路径下的资源需要验证了的用户才能访问
                .antMatchers(SecurityConstants.FILTER_ALL).authenticated()
                .antMatchers(HttpMethod.DELETE, SecurityConstants.FILTER_ALL).hasRole("ADMIN")
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                //添加自定义Filter
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());
        // 防止H2 web 页面的Frame 被拦截
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/auth/login");
        http.headers().frameOptions().disable();

    }

    /**
     * Cors配置优化
     **/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList(SecurityConstants.TOKEN_HEADER));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600l);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
