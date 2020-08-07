package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.Address;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:Address业务层接口
 * @Date
 *****/
@RequestMapping("/address")
@CrossOrigin
public interface IAddressService {

    /***
     * Address多条件分页查询
     * @param address
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<Address> findPage(@SpringQueryMap Address address, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<Address> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * Address多条件搜索方法
     * @param address
     * @return
     */
    @GetMapping("/findList")
    List<Address> findList(@SpringQueryMap Address address);

    /***
     * 删除Address
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Integer id);

    /***
     * 修改Address数据
     * @param address
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody Address address);

    /***
     * 新增Address
     * @param address
     */
    @PostMapping("/add")
    void add(@RequestBody Address address);

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
    @GetMapping("/findById")
     Address findById(@RequestParam("id") Integer id);

    /***
     * 查询所有Address
     * @return
     */
    @GetMapping("/findAll")
    List<Address> findAll();
}
