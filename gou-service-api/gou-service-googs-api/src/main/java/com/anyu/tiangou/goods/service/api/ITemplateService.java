package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Template;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Template业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/template")
public interface ITemplateService {

    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Template> findPage(@SpringQueryMap Template template, @RequestParam("page") int page, @RequestParam("size")  int size);

    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Template> findPage(@RequestParam("page") int page, @RequestParam("size")  int size);

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    @GetMapping("/findList")
    List<Template> findList(@SpringQueryMap Template template);

    /***
     * 删除Template
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Integer id);

    /***
     * 修改Template数据
     * @param template
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Template template);

    /***
     * 新增Template
     * @param template
     */
    @PostMapping("/save")
    void add(@RequestBody Template template);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Template findById(@RequestParam("id") Integer id);

    /***
     * 查询所有Template
     * @return
     */
    @GetMapping("/findAll")
    List<Template> findAll();


    /**
     * 根据分类ID查询模板信息
     * @param id
     * @return
     */
    @GetMapping("/findByCategoryId")
    Template findByCategoryId(@RequestParam("id") Integer id);
}
