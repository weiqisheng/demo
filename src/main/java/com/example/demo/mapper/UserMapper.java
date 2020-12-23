package com.example.demo.mapper;

import com.example.demo.model.UmsAdmin;
import com.example.demo.model.UmsResource;

import java.util.List;

/**
 * @author weiqisheng
 * @Title: UserMapper
 * @ProjectName demo
 * @Description: TODO
 * @date 2020/12/2211:49
 */
public interface UserMapper {

    UmsAdmin findByName(String name);

    List<UmsResource> findById(Long id);

    List<UmsResource> listAll();
}
