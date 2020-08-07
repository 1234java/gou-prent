package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.OauthClientDetails;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:OauthClientDetails业务层接口
 * @Date
 *****/
@RequestMapping("/oauthClientDetails")
@CrossOrigin
public interface IOauthClientDetailsService {

    /***
     * OauthClientDetails多条件分页查询
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<OauthClientDetails> findPage(@SpringQueryMap OauthClientDetails oauthClientDetails, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * OauthClientDetails分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<OauthClientDetails> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * OauthClientDetails多条件搜索方法
     * @param oauthClientDetails
     * @return
     */
    @GetMapping("/findList")
    List<OauthClientDetails> findList(@SpringQueryMap OauthClientDetails oauthClientDetails);

    /***
     * 删除OauthClientDetails
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") String id);

    /***
     * 修改OauthClientDetails数据
     * @param oauthClientDetails
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody OauthClientDetails oauthClientDetails);

    /***
     * 新增OauthClientDetails
     * @param oauthClientDetails
     */
    @PostMapping("/add")
    void add(@RequestBody OauthClientDetails oauthClientDetails);

    /**
     * 根据ID查询OauthClientDetails
     * @param id
     * @return
     */
    @GetMapping("/findById")
     OauthClientDetails findById(@RequestParam("id") String id);

    /***
     * 查询所有OauthClientDetails
     * @return
     */
    @GetMapping("/findAll")
    List<OauthClientDetails> findAll();
}
