package com.anyu.tiangou.goods.mapper;


import com.anyu.tiangou.goods.model.Category;
import com.anyu.tiangou.goods.model.CategoryOptions;
import com.anyu.tiangou.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@author shkstart
@create 2020-07-17 16:45
*/
public interface CategoryMapper extends MyMapper<Category> {


    List<CategoryOptions> optionList( @Param("pid") String pid);

}