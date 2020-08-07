package com.anyu.tiangou.user.mdoel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Address构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_address")
@Data
@ToString
public class Address implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

    @Column(name = "username")
	private String username;//用户名

    @Column(name = "provinceid")
	private String provinceid;//省

    @Column(name = "cityid")
	private String cityid;//市

    @Column(name = "areaid")
	private String areaid;//县/区

    @Column(name = "phone")
	private String phone;//电话

    @Column(name = "address")
	private String address;//详细地址

    @Column(name = "contact")
	private String contact;//联系人

    @Column(name = "is_default")
	private String isDefault;//是否是默认 1默认 0否

    @Column(name = "alias")
	private String alias;//别名

    public Address(String username, String provinceid, String cityid, String areaid, String phone, String address, String contact, String isDefault, String alias) {
        this.username = username;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.areaid = areaid;
        this.phone = phone;
        this.address = address;
        this.contact = contact;
        this.isDefault = isDefault;
        this.alias = alias;
    }

    public Address() {
    }
}
