package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author weiqisheng
 * @Title: User
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1111:58
 */
@Data
@Builder
public class User {

    private String id;

    private String userName;

    private String password;
}
