package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Goods;
import com.anyu.tiangou.goods.model.Spu;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/spu")
public interface ISpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Spu> findPage(@SpringQueryMap  Spu spu, @RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Spu> findPage(@RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    @GetMapping("/findList")
    List<Spu> findList(@SpringQueryMap Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    @PostMapping("/save")
    void add(@RequestBody Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Spu findById(@RequestParam("id")Long id);

    /***
     * 查询所有Spu
     * @return
     */
    @GetMapping("/findAll")
    List<Spu> findAll();

    /**
     * 添加商品(SPU+ SKUlIST)
     * @param goods   update  add
     */
    @PostMapping("/add")
    void save(@RequestBody Goods goods);
    @GetMapping("/findGoodsById")
    Goods findGoodsById(@RequestParam("id") Long id);
    @GetMapping("/auditSpu")
    void auditSpu(@RequestParam("id") Long id);

    /**
     * 下架
     * @param id
     */
    @GetMapping("/pullSpu")
    void pullSpu(@RequestParam("id") Long id);
    @GetMapping("/logicDeleteSpu")
    void logicDeleteSpu(@RequestParam("id") Long id);

    @GetMapping("/restoreSpu")
    void restoreSpu(@RequestParam("id") Long id);
}
