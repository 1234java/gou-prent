package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Para;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Para业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/para")
public interface IParaService {

    /***
     * Para多条件分页查询
     * @param para
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Para> findPage(@SpringQueryMap Para para, @RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Para> findPage(@RequestParam("page")int page, @RequestParam("size")int size);

    /***
     * Para多条件搜索方法
     * @param para
     * @return
     */
    @GetMapping("/findList")
    List<Para> findList(@SpringQueryMap Para para);

    /***
     * 删除Para
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id")Integer id);

    /***
     * 修改Para数据
     * @param para
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Para para);

    /***
     * 新增Para
     * @param para
     */
    @PostMapping("/save")
    void add(@RequestBody Para para);

    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Para findById(@RequestParam("id")Integer id);

    /***
     * 查询所有Para
     * @return
     */
    @GetMapping("/findAll")
    List<Para> findAll();

    @GetMapping("/findParaByCateogryId")
    List<Para> findParaByCateogryId(@RequestParam("id") Integer id);

}
