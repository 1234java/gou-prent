package com.anyu.tiangou.oauthserve.integration;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 17:31
 */
@Data
public class SysUserAuthentication  implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String email;
    private String phone;

    private String phoneNumber;

    private String status;

    private String name;

    private String type;
}
