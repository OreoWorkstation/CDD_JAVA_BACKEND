package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.entity.Weight;
import ink.scotty.cdd.service.PetService;
import ink.scotty.cdd.service.WeightService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Weight)表控制层
 *
 * @author Scott
 * @author Kai
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
    @Resource
    private PetService petService;
//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param weight 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R<?> selectAll(Page<Weight> page, Weight weight) {
//        return success(this.weightService.page(page, new QueryWrapper<>(weight)));
//    }
    /**
     * 通过主键查询单条数据
     * 4. 获取单个体重
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.weightService.getById(id));
    }

    /**
     * 1. 添加体重
     * @param weight 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Weight weight) {
        boolean flag = this.weightService.save(weight);
        if(flag == false){
            return success(false);
        }else{
            this.updateWeight(weight);
            return success(true);
        }
    }

    /**
     * 5. 修改体重
     * @param weight 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Weight weight) {
        boolean flag = this.weightService.updateById(weight);
        if(flag == true){
            this.updateWeight(weight);
            return success(true);
        }else{
            return success(false);
        }
    }

    /**
     * 2. 删除体重
     * @param weightId
     * @return
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("weight_id") Long weightId) {
        QueryWrapper<Weight> wrapper = new QueryWrapper<>();
        wrapper.eq("id", weightId);
        int cnt = this.weightService.count(wrapper);
        if(cnt == 0){
            return success(false);
        }else{
            Weight weight = this.weightService.getById(weightId);
            this.weightService.removeById(weightId);
            this.updateWeight(weight);
            return success(true);
        }
    }

    private void updateWeight(Weight weight){
        QueryWrapper<Weight> wrapper = new QueryWrapper<>();
        wrapper
                .eq("pet_id", weight.getPetId())
                .orderByDesc("create_time");
        List<Weight> weights = weightService.list(wrapper);
//        for(Weight weighti: weights){
//            System.out.println("############################" + weighti.getWeightValue());
//        }
        Pet pet = petService.getById(weight.getPetId());
        if(weights.size() == 0){
            pet.setWeight(0.0);
        }else{
            pet.setWeight(weights.get(0).getWeightValue());
        }
        petService.updateById(pet);
    }

    /**
     * 3. 获取体重列表
     * @param petId
     * @return
     */
    @GetMapping
    public R<?> getWeightListByPetId(@RequestParam("pet_id") Long petId){
        QueryWrapper<Weight> wrapper = new QueryWrapper<>();
        wrapper.eq("pet_id", petId)
                .orderByDesc("create_time");
        return success(this.weightService.list(wrapper));
    }
}