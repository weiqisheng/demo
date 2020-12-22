package com.example.demo.service.impl;


import com.example.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author weiqisheng
 * @Title: UserDetailsServiceImpl
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1111:57
 */
@Service
public class  UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isBlank(userName)){
            throw new RuntimeException("用户账户不能为空！！");
        }
        User user = User.builder().userName("admin").password(passwordEncoder.encode("123456")).build();
        if (Objects.isNull(user)){
            throw new RuntimeException("用户账户不存在！！");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_111"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}
