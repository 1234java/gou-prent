package com.anyu.tiangou.goods.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author shkstart Administrator
 * @create 2020-08-06 17:19
 */
@Data
@ToString
public class CategoryOptions implements Serializable {



    private Integer value;
    private String label;
    private Integer  parentId;
    private List<CategoryOptions> children;





}
