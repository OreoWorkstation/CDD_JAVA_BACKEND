package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Browse;
import ink.scotty.cdd.service.BrowseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Browse)表控制层
 *
 * @author Scott
 * @since 2020-05-19 15:44:07
 */
@RestController
@RequestMapping("browse")
public class BrowseController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BrowseService browseService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param browse 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Browse> page, Browse browse) {
        return success(this.browseService.page(page, new QueryWrapper<>(browse)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.browseService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param browse 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Browse browse) {
        return success(this.browseService.save(browse));
    }

    /**
     * 修改数据
     *
     * @param browse 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Browse browse) {
        return success(this.browseService.updateById(browse));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.browseService.removeByIds(idList));
    }
}