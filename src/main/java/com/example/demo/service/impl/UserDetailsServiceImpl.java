package com.example.demo.service.impl;


import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
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

import javax.annotation.Resource;
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


    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取用户信息
        UmsAdmin admin = userMapper.findByName(userName);
        if (Objects.isNull(admin)){
            throw new ApiException("该用户不存在！！");
        }
        List<UmsResource> resourceList = userMapper.findById(admin.getId());
        return new AdminUserDetails(admin,resourceList);

    }
}
