package com.anyu.tiangou.user.mdoel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Provinces构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_provinces")
@Data
@ToString
public class Provinces implements Serializable{

	@Id
    @Column(name = "provinceid")
	private String provinceid;//省份ID

    @Column(name = "province")
	private String province;//省份名称


	public Provinces(String provinceid, String province) {
		this.provinceid = provinceid;
		this.province = province;
	}


	public Provinces() {
	}
}
