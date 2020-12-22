package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiqisheng
 * @Title: TokenVo
 * @ProjectName myProject
 * @Description: TODO
 * @date 2020/12/1115:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    private String token;
}
