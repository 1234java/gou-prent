package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Category;
import com.anyu.tiangou.goods.model.CategoryOptions;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Category业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/category")
public interface ICategoryService {

    /***
     * Category多条件分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Category> findPage(@SpringQueryMap Category category, @RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Category> findPage(@RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Category多条件搜索方法
     * @param category
     * @return
     */
    @GetMapping("/findList")
    List<Category> findList(@SpringQueryMap Category category);

    /***
     * 删除Category
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id")Integer id);

    /***
     * 修改Category数据
     * @param category
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Category category);

    /***
     * 新增Category
     * @param category
     */
    @PostMapping("/save")
    void add(@RequestBody Category category);

    /**
     * 根据ID查询Category
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Category findById(@RequestParam("id")Integer id);

    /***
     * 查询所有Category
     * @return
     */
    @GetMapping("/findAll")
    List<Category> findAll();

    @GetMapping("/findByParentId")
    List<Category> findByParentId(@RequestParam("pid") Integer pid);


    @GetMapping("/findByPid")
    List<CategoryOptions> findByPid(@RequestParam("pid") String pid);
}
