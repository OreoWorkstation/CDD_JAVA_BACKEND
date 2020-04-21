package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.service.InstantService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Instant)表控制层
 *
 * @author Scott
 * @since 2020-04-21 22:40:38
 */
@RestController
@RequestMapping("instant")
public class InstantController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private InstantService instantService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param instant 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Instant> page, Instant instant) {
        return success(this.instantService.page(page, new QueryWrapper<>(instant)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.instantService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param instant 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Instant instant) {
        return success(this.instantService.save(instant));
    }

    /**
     * 修改数据
     *
     * @param instant 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Instant instant) {
        return success(this.instantService.updateById(instant));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.instantService.removeByIds(idList));
    }
}