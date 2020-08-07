package ${package_service};
import ${package_pojo}.${Table};
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.SpringQueryMap;
/****
 * @Author:admin
 * @Description:${Table}业务层接口
 * @Date
 *****/
@RequestMapping("/${table}")
@CrossOrigin
public interface I${Table}Service {

    /***
     * ${Table}多条件分页查询
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findPage")
    PageInfo<${Table}> findPage(@SpringQueryMap ${Table} ${table}, @RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * ${Table}分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findAllPage")
    PageInfo<${Table}> findPage(@RequestParam("page") int page,@RequestParam("size") int size);

    /***
     * ${Table}多条件搜索方法
     * @param ${table}
     * @return
     */
    @GetMapping("/findList")
    List<${Table}> findList(@SpringQueryMap ${Table} ${table});

    /***
     * 删除${Table}
     * @param id
     */
    @GetMapping("/delete")
    void delete(@RequestParam("id") ${keyType} id);

    /***
     * 修改${Table}数据
     * @param ${table}
     */
    @PostMapping("/updateInfo")
    void update(@RequestBody ${Table} ${table});

    /***
     * 新增${Table}
     * @param ${table}
     */
    @PostMapping("/add")
    void add(@RequestBody ${Table} ${table});

    /**
     * 根据ID查询${Table}
     * @param id
     * @return
     */
    @GetMapping("/findById")
     ${Table} findById(@RequestParam("id") ${keyType} id);

    /***
     * 查询所有${Table}
     * @return
     */
    @GetMapping("/findAll")
    List<${Table}> findAll();
}
