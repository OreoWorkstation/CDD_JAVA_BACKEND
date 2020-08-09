package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.entity.User;
import ink.scotty.cdd.service.PetService;
import ink.scotty.cdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Pet)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:40:55
 */
@RestController
@RequestMapping("pet")
public class PetController extends ApiController {


    /**
     * 服务对象
     */
    @Resource
    private PetService petService;
    @Resource
    private UserService userService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pet 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R<?> selectAll(Page<Pet> page, Pet pet) {
//        return success(this.petService.page(page, new QueryWrapper<>(pet)));
//    }

    /**
     * 通过主键查询单条数据
     * 4. 获取单个宠物
     * @param pet_id 主键
     * @return 单条数据
     */
    @GetMapping("{pet_id}")
    public R<?> selectOne(@PathVariable Serializable pet_id) {
        return success(this.petService.getById(pet_id));
    }

    /**
     * 新增数据
     * 1. 添加宠物
     * @param pet 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Pet pet) {
        boolean flag = this.petService.save(pet);
        if(flag == true){
            User user = userService.getById(pet.getUserId());
            user.setPetNumber(user.getPetNumber() + 1);
            userService.updateById(user);
            return success(true);
        }else{
            return success(false);
        }
    }

    /**
     * 5. 修改宠物信息
     * @param pet 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Pet pet) {
        return success(this.petService.updateById(pet));
    }

    /**
     * 2. 删除宠物
     * @param petId
     * @return
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("pet_id") Long petId) {
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.eq("id", petId);
        int cnt = this.petService.count(wrapper);
        if(cnt == 0){   //判断是否存在 不存在
            return success(false);
        } else{ //存在
            Pet pet = this.petService.getById(petId);
            boolean b = this.petService.removeById(petId);
            Long userId = pet.getUserId();
            User user = this.userService.getById(userId);
            user.setPetNumber(user.getPetNumber() - 1);
            this.userService.updateById(user);
            return success(b);
        }
    }

    /**
     * 3. 根据UserId获取宠物列表
     * @param userId
     * @return
     */
    @GetMapping
    public R<?> getPetListByUserId(@RequestParam("user_id") Long userId){
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper
                .eq("user_id", userId);
        return success(this.petService.list(wrapper));
    }
}