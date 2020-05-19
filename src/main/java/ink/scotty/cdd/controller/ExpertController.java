package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Expert;
import ink.scotty.cdd.service.ExpertService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Expert)表控制层
 *
 * @author Scott
 * @since 2020-05-19 15:43:02
 */
@RestController
@RequestMapping("expert")
public class ExpertController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ExpertService expertService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param expert 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Expert> page, Expert expert) {
        return success(this.expertService.page(page, new QueryWrapper<>(expert)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.expertService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param expert 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Expert expert) {
        return success(this.expertService.save(expert));
    }

    /**
     * 修改数据
     *
     * @param expert 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Expert expert) {
        return success(this.expertService.updateById(expert));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.expertService.removeByIds(idList));
    }
}