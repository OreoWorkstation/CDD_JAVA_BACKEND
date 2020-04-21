package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Weight;
import ink.scotty.cdd.service.WeightService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Weight)表控制层
 *
 * @author Scott
 * @since 2020-04-21 22:41:21
 */
@RestController
@RequestMapping("weight")
public class WeightController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WeightService weightService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param weight 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Weight> page, Weight weight) {
        return success(this.weightService.page(page, new QueryWrapper<>(weight)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.weightService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param weight 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Weight weight) {
        return success(this.weightService.save(weight));
    }

    /**
     * 修改数据
     *
     * @param weight 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Weight weight) {
        return success(this.weightService.updateById(weight));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.weightService.removeByIds(idList));
    }
}