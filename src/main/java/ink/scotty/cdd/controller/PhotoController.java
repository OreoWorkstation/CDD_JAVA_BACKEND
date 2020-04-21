package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Photo;
import ink.scotty.cdd.service.PhotoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Photo)表控制层
 *
 * @author Scott
 * @since 2020-04-21 22:41:05
 */
@RestController
@RequestMapping("photo")
public class PhotoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PhotoService photoService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param photo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Photo> page, Photo photo) {
        return success(this.photoService.page(page, new QueryWrapper<>(photo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.photoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param photo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Photo photo) {
        return success(this.photoService.save(photo));
    }

    /**
     * 修改数据
     *
     * @param photo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Photo photo) {
        return success(this.photoService.updateById(photo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.photoService.removeByIds(idList));
    }
}