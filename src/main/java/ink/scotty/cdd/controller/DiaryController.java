package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Diary;
import ink.scotty.cdd.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Diary)表控制层
 *
 * @author Scott
 * @since 2020-04-21 22:40:09
 */
@RestController
@RequestMapping("diary")
public class DiaryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DiaryService diaryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param diary 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Diary> page, Diary diary) {
        return success(this.diaryService.page(page, new QueryWrapper<>(diary)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.diaryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param diary 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Diary diary) {
        return success(this.diaryService.save(diary));
    }

    /**
     * 修改数据
     *
     * @param diary 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Diary diary) {
        return success(this.diaryService.updateById(diary));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.diaryService.removeByIds(idList));
    }
}