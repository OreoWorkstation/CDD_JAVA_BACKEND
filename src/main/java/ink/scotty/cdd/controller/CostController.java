package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Cost;
import ink.scotty.cdd.service.CostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Cost)表控制层
 *
 * @author Scott
 * @since 2020-04-21 22:40:01
 */
@RestController
@RequestMapping("cost")
public class CostController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CostService costService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param cost 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Cost> page, Cost cost) {
        return success(this.costService.page(page, new QueryWrapper<>(cost)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.costService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param cost 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Cost cost) {
        return success(this.costService.save(cost));
    }

    /**
     * 修改数据
     *
     * @param cost 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Cost cost) {
        return success(this.costService.updateById(cost));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.costService.removeByIds(idList));
    }
}