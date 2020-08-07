package com.anyu.tiangou.goods.service.api;

import com.anyu.tiangou.goods.model.Album;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Album业务层接口
 * @Date 2019/6/14 0:16
 *****/
@RequestMapping("/album")
@CrossOrigin
public interface IAlbumService {

    /***
     * Album多条件分页查询
     * @param album
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Album> findPage(@SpringQueryMap Album album, @RequestParam("page")int page, @RequestParam("size") int size);

    /***
     * Album分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Album> findPage(@RequestParam("page")int page, @RequestParam("size") int size);

    /***
     * Album多条件搜索方法
     * @param album
     * @return
     */
    @GetMapping("/findList")
    List<Album> findList(@SpringQueryMap Album album);

    /***
     * 删除Album
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id")Long id);

    /***
     * 修改Album数据
     * @param album
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Album album);

    /***
     * 新增Album
     * @param album
     */
    @PostMapping("/save")
    void add(@RequestBody Album album);

    /**
     * 根据ID查询Album
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Album findById(@RequestParam("id") Long id);

    /***
     * 查询所有Album
     * @return
     */
    @GetMapping("/findAll")
    List<Album> findAll();
}
