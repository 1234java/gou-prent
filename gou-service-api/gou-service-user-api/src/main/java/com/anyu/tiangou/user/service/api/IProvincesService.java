package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.Provinces;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Provinces业务层接口
 * @Date
 *****/
@RequestMapping("/provinces")
@CrossOrigin
public interface IProvincesService {

    /***
     * Provinces多条件分页查询
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Provinces> findPage(@SpringQueryMap Provinces provinces, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Provinces分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Provinces> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * Provinces多条件搜索方法
     * @param provinces
     * @return
     */
    @GetMapping("/findList")
    List<Provinces> findList(@SpringQueryMap Provinces provinces);

    /***
     * 删除Provinces
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改Provinces数据
     * @param provinces
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Provinces provinces);

    /***
     * 新增Provinces
     * @param provinces
     */
    @PostMapping("/add")
    void add(@RequestBody Provinces provinces);

    /**
     * 根据ID查询Provinces
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Provinces findById(@RequestParam("id") String id);

    /***
     * 查询所有Provinces
     * @return
     */
    @GetMapping("/findAll")
    List<Provinces> findAll();
}
