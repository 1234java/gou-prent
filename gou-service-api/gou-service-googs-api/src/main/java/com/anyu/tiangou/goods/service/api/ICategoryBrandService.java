package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.CategoryBrand;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:CategoryBrand业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/categorybrand")
public interface ICategoryBrandService {

    /***
     * CategoryBrand多条件分页查询
     * @param categoryBrand
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<CategoryBrand> findPage(@SpringQueryMap CategoryBrand categoryBrand, @RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * CategoryBrand分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<CategoryBrand> findPage(@RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * CategoryBrand多条件搜索方法
     * @param categoryBrand
     * @return
     */
    @GetMapping("/findList")
    List<CategoryBrand> findList(@SpringQueryMap CategoryBrand categoryBrand);

    /***
     * 删除CategoryBrand
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id")Integer id);

    /***
     * 修改CategoryBrand数据
     * @param categoryBrand
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody CategoryBrand categoryBrand);

    /***
     * 新增CategoryBrand
     * @param categoryBrand
     */
    @PostMapping("/save")
    void add(@RequestBody CategoryBrand categoryBrand);

    /**
     * 根据ID查询CategoryBrand
     * @param id
     * @return
     */
    @GetMapping("/findById")
     CategoryBrand findById(@RequestParam("id") Integer id);

    /***
     * 查询所有CategoryBrand
     * @return
     */
    @GetMapping("/findAll")
    List<CategoryBrand> findAll();
}
