package com.anyu.tiangou.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 11:51
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
