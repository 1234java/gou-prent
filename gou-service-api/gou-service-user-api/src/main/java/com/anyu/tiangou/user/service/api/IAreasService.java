package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.Areas;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Areas业务层接口
 * @Date
 *****/
@RequestMapping("/areas")
@CrossOrigin
public interface IAreasService {

    /***
     * Areas多条件分页查询
     * @param areas
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Areas> findPage(@SpringQueryMap Areas areas, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Areas> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * Areas多条件搜索方法
     * @param areas
     * @return
     */
    @GetMapping("/findList")
    List<Areas> findList(@SpringQueryMap Areas areas);

    /***
     * 删除Areas
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改Areas数据
     * @param areas
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Areas areas);

    /***
     * 新增Areas
     * @param areas
     */
    @PostMapping("/add")
    void add(@RequestBody Areas areas);

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Areas findById(@RequestParam("id") String id);

    /***
     * 查询所有Areas
     * @return
     */
    @GetMapping("/findAll")
    List<Areas> findAll();
}
