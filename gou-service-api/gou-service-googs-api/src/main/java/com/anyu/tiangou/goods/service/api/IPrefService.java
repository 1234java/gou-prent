package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Pref;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Pref业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/pref")
public interface IPrefService {

    /***
     * Pref多条件分页查询
     * @param pref
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Pref> findPage(@SpringQueryMap Pref pref, @RequestParam("page")int page, @RequestParam("size)")int size);

    /***
     * Pref分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Pref> findPage(@RequestParam("page")int page, @RequestParam("size)")int size);

    /***
     * Pref多条件搜索方法
     * @param pref
     * @return
     */
    @GetMapping("/findList")
    List<Pref> findList(@SpringQueryMap Pref pref);

    /***
     * 删除Pref
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id")Integer id);

    /***
     * 修改Pref数据
     * @param pref
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Pref pref);


    /***
     * 新增Pref
     * @param pref
     */
    @PostMapping("/save")
    void add(@RequestBody Pref pref);

    /**
     * 根据ID查询Pref
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Pref findById(@RequestParam("id") Integer id);

    /***
     * 查询所有Pref
     * @return
     */
    @GetMapping("/findAll")
    List<Pref> findAll();
}
