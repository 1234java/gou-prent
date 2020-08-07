package com.anyu.tiangou.user.service.api;

import com.anyu.tiangou.user.mdoel.UndoLog;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:UndoLog业务层接口
 * @Date
 *****/
@RequestMapping("/undoLog")
@CrossOrigin
public interface IUndoLogService {

    /***
     * UndoLog多条件分页查询
     * @param undoLog
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<UndoLog> findPage(@SpringQueryMap UndoLog undoLog, @RequestParam("page") int page, @RequestParam("size") int size);

    /***
     * UndoLog分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<UndoLog> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * UndoLog多条件搜索方法
     * @param undoLog
     * @return
     */
    @GetMapping("/findList")
    List<UndoLog> findList(@SpringQueryMap UndoLog undoLog);

    /***
     * 删除UndoLog
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") Long id);

    /***
     * 修改UndoLog数据
     * @param undoLog
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody UndoLog undoLog);

    /***
     * 新增UndoLog
     * @param undoLog
     */
    @PostMapping("/add")
    void add(@RequestBody UndoLog undoLog);

    /**
     * 根据ID查询UndoLog
     * @param id
     * @return
     */
    @GetMapping("/findById")
     UndoLog findById(@RequestParam("id") Long id);

    /***
     * 查询所有UndoLog
     * @return
     */
    @GetMapping("/findAll")
    List<UndoLog> findAll();
}
