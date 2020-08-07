package com.anyu.tiangou.user.mdoel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Cities构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_cities")
@Data
@ToString
public class Cities implements Serializable{

	@Id
    @Column(name = "cityid")
	private String cityid;//城市ID

    @Column(name = "city")
	private String city;//城市名称

    @Column(name = "provinceid")
	private String provinceid;//省份ID

	public Cities(String cityid, String city, String provinceid) {
		this.cityid = cityid;
		this.city = city;
		this.provinceid = provinceid;
	}

	public Cities() {
	}
}
