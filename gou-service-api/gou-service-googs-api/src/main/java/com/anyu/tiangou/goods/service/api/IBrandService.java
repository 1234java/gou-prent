package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Brand;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-07-17 17:00
 */
@RequestMapping("/brand")
@CrossOrigin
public interface IBrandService {


    /***
     * 查询所有品牌
     * @return
     */
    @GetMapping("/all")
    List<Brand> findAll();


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/findById")
    Brand findById(@RequestParam("id") Integer id);

    /***
     * 新增品牌
     * @param brand
     */
    @PostMapping("/save")
    void add(@RequestBody Brand brand);


    /***
     * 修改品牌数据
     * @param brand
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Brand brand);


    /***
     * 删除品牌
     * @param id
     */
    @GetMapping("/del")
    void delete(@RequestParam("id")Integer id);


    /***
     * 多条件搜索品牌方法
     * @param brand
     * @return
     */
    @GetMapping("/list")
    List<Brand> findList(Brand brand);


    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Brand> findPage(@RequestParam("page") int page, @RequestParam("size")int size);



    /***
     * 多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPageAll")
    PageInfo<Brand> findPage(@SpringQueryMap Brand brand, @RequestParam("page") int page, @RequestParam("size")int size);


    /***
     * 根据品牌id查询品牌集合--分类id
     * @param categoryId
     * @return
     */
    @GetMapping("/findByCategoryId")
    List<Brand> findByCategoryId(@SpringQueryMap Integer categoryId);
}
