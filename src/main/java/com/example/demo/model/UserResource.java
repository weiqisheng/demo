package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author weiqisheng
 * @Title: UserResource
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1111:58
 */
@Data
@Builder
public class UserResource {

    private String id;

    private String roleName;

    private String roleCode;
}
