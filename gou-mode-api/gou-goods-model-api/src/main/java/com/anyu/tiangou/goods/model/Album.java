package com.anyu.tiangou.goods.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Album构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_album")
@Data
@ToString
public class Album implements Serializable{

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * 相册名称
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 相册封面
	 */
	@Column(name = "image")
	private String image;

	/**
	 * 图片列表
	 */
	@Column(name = "image_items")
	private String imageItems;

}
