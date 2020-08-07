package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Spec;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Spec业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/spec")
public interface ISpecService {

    /***
     * Spec多条件分页查询
     * @param spec
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Spec> findPage(@SpringQueryMap Spec spec, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Spec分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Spec> findPage(@RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Spec多条件搜索方法
     * @param spec
     * @return
     */
    @GetMapping("/findList")
    List<Spec> findList(@SpringQueryMap Spec spec);

    /***
     * 删除Spec
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Integer id);

    /***
     * 修改Spec数据
     * @param spec
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Spec spec);

    /***
     * 新增Spec
     * @param spec
     */
    @PostMapping("/save")
    void add(@RequestBody Spec spec);

    /**
     * 根据ID查询Spec
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Spec findById(@RequestParam("id") Integer id);

    /***
     * 查询所有Spec
     * @return
     */
    @GetMapping("/findAll")
    List<Spec> findAll();


    /**
     * 根据分类的ID 查询规格的列表数据
     * @param id 三级分类的ID
     * @return
     */
    @GetMapping("/findByCategoryId")
    List<Spec> findByCategoryId(@RequestParam("id") Integer id);
}
