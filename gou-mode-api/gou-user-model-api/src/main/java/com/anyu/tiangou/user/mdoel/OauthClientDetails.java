package com.anyu.tiangou.user.mdoel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:OauthClientDetails构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="oauth_client_details")
@Data
@ToString
public class OauthClientDetails implements Serializable{

	@Id
    @Column(name = "client_id")
	private String clientId;//客户端ID，主要用于标识对应的应用

    @Column(name = "resource_ids")
	private String resourceIds;//

    @Column(name = "client_secret")
	private String clientSecret;//客户端秘钥，BCryptPasswordEncoder加密算法加密

    @Column(name = "scope")
	private String scope;//对应的范围

    @Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;//认证模式

    @Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;//认证后重定向地址

    @Column(name = "authorities")
	private String authorities;//

    @Column(name = "access_token_validity")
	private Integer accessTokenValidity;//令牌有效期

    @Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;//令牌刷新周期

    @Column(name = "additional_information")
	private String additionalInformation;//

    @Column(name = "autoapprove")
	private String autoapprove;//


	public OauthClientDetails(String clientId, String resourceIds, String clientSecret, String scope, String authorizedGrantTypes, String webServerRedirectUri, String authorities, Integer accessTokenValidity, Integer refreshTokenValidity, String additionalInformation, String autoapprove) {
		this.clientId = clientId;
		this.resourceIds = resourceIds;
		this.clientSecret = clientSecret;
		this.scope = scope;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.webServerRedirectUri = webServerRedirectUri;
		this.authorities = authorities;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInformation = additionalInformation;
		this.autoapprove = autoapprove;
	}


	public OauthClientDetails() {
	}
}
