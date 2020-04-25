package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.entity.Photo;
import ink.scotty.cdd.service.PetService;
import ink.scotty.cdd.service.PhotoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Photo)表控制层
 *
 * @author Scott
 * @author Kai
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
    @Resource
    private PetService petService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param photo 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R<?> selectAll(Page<Photo> page, Photo photo) {
//        return success(this.photoService.page(page, new QueryWrapper<>(photo)));
//    }

    /**
     * 4. 获取单个相片
     * @param photo_id 主键
     * @return 单条数据
     */
    @GetMapping("{photo_id}")
    public R<?> selectOne(@PathVariable Serializable photo_id) {
        return success(this.photoService.getById(photo_id));
    }

    /**
     * 1. 添加相片
     * @param photo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Photo photo) {
        boolean flag = this.photoService.save(photo);
        if(flag == false){
            return success(false);
        }else{
            Pet pet = this.petService.getById(photo.getPetId());
            pet.setPhotoNumber(pet.getPhotoNumber() + 1);
            this.petService.updateById(pet);
            return success(true);
        }
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
     * 3. 根据pet_id获取相片列表
     * @param petId
     * @return
     */
    @GetMapping
    public R<?> getPetListByUserId(@RequestParam("pet_id") Long petId){
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        wrapper
                .eq("pet_id", petId)
                .orderByDesc("create_time");
        return success(this.photoService.list(wrapper));
    }

    @DeleteMapping
    public R<?> delete(@RequestParam("photo_id") Long photoId) {
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", photoId);
        int cnt = this.photoService.count(wrapper);
        if(cnt == 0){   //判断是否存在 不存在
            return success(false);
        } else{ //存在
            Photo photo = this.photoService.getById(photoId);
            this.photoService.removeById(photoId);
            Pet pet = this.petService.getById(photo.getPetId());
            pet.setPhotoNumber(pet.getPhotoNumber() - 1);
            this.petService.updateById(pet);
            return success(true);
        }
    }
}