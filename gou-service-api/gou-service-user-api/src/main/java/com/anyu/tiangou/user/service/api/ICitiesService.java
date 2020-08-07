package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.Cities;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Cities业务层接口
 * @Date
 *****/
@RequestMapping("/cities")
@CrossOrigin
public interface ICitiesService {

    /***
     * Cities多条件分页查询
     * @param cities
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Cities> findPage(@SpringQueryMap Cities cities, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Cities> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * Cities多条件搜索方法
     * @param cities
     * @return
     */
    @GetMapping("/findList")
    List<Cities> findList(@SpringQueryMap Cities cities);

    /***
     * 删除Cities
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改Cities数据
     * @param cities
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Cities cities);

    /***
     * 新增Cities
     * @param cities
     */
    @PostMapping("/add")
    void add(@RequestBody Cities cities);

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Cities findById(@RequestParam("id") String id);

    /***
     * 查询所有Cities
     * @return
     */
    @GetMapping("/findAll")
    List<Cities> findAll();
}
