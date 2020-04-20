package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.service.PetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Pet)表控制层
 *
 * @author Scott
 * @since 2020-04-20 18:52:07
 */
@RestController
@RequestMapping("pet")
public class PetController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PetService petService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pet 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Pet> page, Pet pet) {
        return success(this.petService.page(page, new QueryWrapper<>(pet)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.petService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pet 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Pet pet) {
        return success(this.petService.save(pet));
    }

    /**
     * 修改数据
     *
     * @param pet 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Pet pet) {
        return success(this.petService.updateById(pet));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.petService.removeByIds(idList));
    }
}