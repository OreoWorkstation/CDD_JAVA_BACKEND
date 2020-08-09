package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Cost;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.entity.Weight;
import ink.scotty.cdd.service.CostService;
import ink.scotty.cdd.service.PetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Cost)表控制层
 *
 * @author Scott
 * @author Kai
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
    @Resource
    private PetService petService;

//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param cost 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R<?> selectAll(Page<Cost> page, Cost cost) {
//        return success(this.costService.page(page, new QueryWrapper<>(cost)));
//    }

    /**
     * 4. 获取单个消费信息
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.costService.getById(id));
    }

    /**
     * 1. 添加消费信息
     * @param cost 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Cost cost) {
        boolean flag = this.costService.save(cost);
        if(flag == false){
            return success(false);
        }else{
            Pet pet = petService.getById(cost.getPetId());
            pet.setTotalCost(pet.getTotalCost() + cost.getCostValue());
            petService.updateById(pet);
            return success(true);
        }
    }

    /**
     * 5. 修改消费信息
     *
     * @param cost 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Cost cost) {
        boolean flag = this.costService.updateById(cost);
        if(flag == false){
            return success(false);
        }else {
            double total = 0.0;
            QueryWrapper<Cost> wrapper = new QueryWrapper<>();
            wrapper.eq("pet_id", cost.getPetId());
            List<Cost> ls = this.costService.list(wrapper);
            for(Cost li: ls){
                total += li.getCostValue();
            }
            Pet pet = this.petService.getById(cost.getPetId());
            pet.setTotalCost(total);
            this.petService.updateById(pet);
            return success(true);
        }
    }

    /**
     * 2. 删除消费
     * @param costId 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("cost_id") Long costId) {
        QueryWrapper<Cost> wrapper = new QueryWrapper<>();
        wrapper.eq("id", costId);
        int cnt = this.costService.count(wrapper);
        if(cnt == 0){
            return success(false);
        }else{
            Cost cost = this.costService.getById(costId);
            this.costService.removeById(costId);
            Pet pet = this.petService.getById(cost.getPetId());
            pet.setTotalCost(pet.getTotalCost() - cost.getCostValue());
            this.petService.updateById(pet);
            return success(true);
        }
    }

    /**
     * 3. 获取消费信息列表
     * @param petId
     * @return
     */
    @GetMapping
    public R<?> getCostListByPetId(@RequestParam("pet_id") Long petId){
        QueryWrapper<Cost> wrapper = new QueryWrapper<>();
        wrapper.eq("pet_id", petId)
                .orderByDesc("create_time");
        return success(this.costService.list(wrapper));
    }
}