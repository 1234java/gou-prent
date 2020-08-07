package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Sku;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Sku业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/sku")
public interface ISkuService {

    /***
     * Sku多条件分页查询
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Sku> findPage(@SpringQueryMap @RequestBody Sku sku, @RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Sku分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Sku> findPage(@RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Sku多条件搜索方法
     * @param sku
     * @return
     */
    @GetMapping("/findList")
    List<Sku> findList(@SpringQueryMap Sku sku);

    /***
     * 删除Sku
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Long id);

    /***
     * 修改Sku数据
     * @param sku
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Sku sku);

    /***
     * 新增Sku
     * @param sku
     */
    @PostMapping("/save")
    void add(@RequestBody Sku sku);

    /**
     * 根据ID查询Sku
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Sku findById(@RequestParam("id")Long id);

    /***
     * 查询所有Sku
     * @return
     */
    @GetMapping("/findAll")
    List<Sku> findAll();

    /**
     * 查询符合条件的eSKU的列表数据
     * @param status
     * @return
     */
    @GetMapping("/findByStatus")
    List<Sku> findByStatus(@RequestParam("status") String status);

    /**
     * 递减库存
     * @param username
     * @return
     */
    @GetMapping("/decrCount")
    void decrCount(@RequestParam("username") String username);

}
