package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.StockBack;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:StockBack业务层接口
 * @Date 2019/6/14 0:16
 *****/
@CrossOrigin
@RequestMapping("/stockback")
public interface IStockBackService {

    /***
     * StockBack多条件分页查询
     * @param stockBack
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<StockBack> findPage(@SpringQueryMap StockBack stockBack, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * StockBack分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<StockBack> findPage(@RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * StockBack多条件搜索方法
     * @param stockBack
     * @return
     */
    @GetMapping("/findList")
    List<StockBack> findList(@SpringQueryMap StockBack stockBack);

    /***
     * 删除StockBack
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改StockBack数据
     * @param stockBack
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody StockBack stockBack);

    /***
     * 新增StockBack
     * @param stockBack
     */
    @PostMapping("/save")
    void add(@RequestBody StockBack stockBack);

    /**
     * 根据ID查询StockBack
     * @param id
     * @return
     */
    @GetMapping("/findById")
     StockBack findById(@RequestParam("id") String id);

    /***
     * 查询所有StockBack
     * @return
     */
    @GetMapping("/findAll")
    List<StockBack> findAll();
}
