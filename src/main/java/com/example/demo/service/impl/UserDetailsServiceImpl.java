package com.example.demo.service.impl;


import com.example.demo.model.AdminUserDetails;
import com.example.demo.model.UmsAdmin;
import com.example.demo.model.UmsResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        //获取用户信息
        UmsAdmin admin = new UmsAdmin();
        admin.setUsername("admin");
        admin.setStatus(1);
        admin.setPassword(passwordEncoder.encode("123456"));
        List<UmsResource> resourceList = new ArrayList<>();
        return new AdminUserDetails(admin,resourceList);

    }
}
