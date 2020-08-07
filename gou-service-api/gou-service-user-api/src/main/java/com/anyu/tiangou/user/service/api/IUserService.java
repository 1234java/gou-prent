package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:User业务层接口
 * @Date
 *****/
@RequestMapping("/user")
@CrossOrigin
public interface IUserService {

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<User> findPage(@SpringQueryMap User user, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<User> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    @GetMapping("/findList")
    List<User> findList(@SpringQueryMap User user);

    /***
     * 删除User
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改User数据
     * @param user
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody User user);

    /***
     * 新增User
     * @param user
     */
    @PostMapping("/add")
    void add(@RequestBody User user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @GetMapping("/findById")
     User findById(@RequestParam("id") String id);

    /**
     * 使用用户名查询信息
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    User findByName(@RequestParam("name") String name);

    /**
     * 使用用户名查询信息
     * @param phone
     * @return
     */
    @GetMapping("/findByPhone")
    User findByPhone(@RequestParam("phone") String phone);
    /***
     * 查询所有User
     * @return
     */
    @GetMapping("/findAll")
    List<User> findAll();
}
