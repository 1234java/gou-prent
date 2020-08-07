package com.anyu.tiangou.oauthserve.config;

import lombok.Data;
import lombok.ToString;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 11:35
 */
@Data
@ToString
public class UserInfo {

    private String id;
    private  String username;
    private String qqnum;
    private String  userFlag;

    public UserInfo(String id, String username, String qqnum, String userFlag) {
        this.id = id;
        this.username = username;
        this.qqnum = qqnum;
        this.userFlag = userFlag;
    }

    public UserInfo() {
    }
}
