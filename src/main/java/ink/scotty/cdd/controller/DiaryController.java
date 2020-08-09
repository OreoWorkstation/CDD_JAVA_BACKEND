package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Diary;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.service.DiaryService;
import ink.scotty.cdd.service.PetService;
import ink.scotty.cdd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Diary)表控制层
 *
 * @author Scott
 * @author Kai
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
    @Resource
    private PetService petService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param diary 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R<?> selectAll(Page<Diary> page, Diary diary) {
//        return success(this.diaryService.page(page, new QueryWrapper<>(diary)));
//    }

    /**
     * 通过主键查询单条数据
     * 4. 获取单个日记
     * @param diary_id 主键
     * @return 单条数据
     */
    @GetMapping("{diary_id}")
    public R<?> selectOne(@PathVariable Serializable diary_id) {
        return success(this.diaryService.getById(diary_id));
    }

    /**
     * 新增数据
     * 1. 添加日记
     * @param diary 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Diary diary) {
        boolean flag = this.diaryService.save(diary);
        if(flag == true){
            Long petId = diary.getPetId();
            Pet pet = petService.getById(petId);
            pet.setDiaryNumber(pet.getDiaryNumber() + 1);
            this.petService.updateById(pet);
            return success(true);
        }else{
            return success(false);
        }
    }

    /**
     * 5. 修改日记
     * @param diary 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Diary diary) {
        return success(this.diaryService.updateById(diary));
    }

    /**
     * 删除数据
     * 2. 删除日记
     * @param diaryId 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("diary_id") Long diaryId) {
        QueryWrapper<Diary> wrapper = new QueryWrapper<>();
        wrapper.eq("id", diaryId);
        int cnt = this.diaryService.count(wrapper);

        if(cnt == 0){
            return success(false);
        }else{
            Diary diary = this.diaryService.getById(diaryId);
            this.diaryService.removeById(diaryId);
            Pet pet = this.petService.getById(diary.getPetId());
            pet.setDiaryNumber(pet.getDiaryNumber() - 1);
            this.petService.updateById(pet);
            return success(true);
        }
    }

    /**
     * 3. 获取日记列表
     * @param petId
     * @return
     */
    @GetMapping
    public R<?> getDiaryListByPetId(@RequestParam("pet_id") Long petId){
        QueryWrapper<Diary> wrapper = new QueryWrapper<>();
        wrapper.eq("pet_id", petId)
                .orderByDesc("create_time");
        return success(this.diaryService.list(wrapper));
    }
}