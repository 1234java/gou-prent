package com.anyu.tiangou.goods.mapper;

import com.anyu.tiangou.goods.model.Brand;
import com.anyu.tiangou.mapper.MyMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
@author shkstart
@create 2020-07-17 16:45
*/
public interface BrandMapper extends MyMapper<Brand> {


    List<Brand> findByCategoryId(Integer categoryId);




}