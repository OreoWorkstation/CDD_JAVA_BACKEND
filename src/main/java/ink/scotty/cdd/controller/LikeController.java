package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.entity.Like;
import ink.scotty.cdd.service.InstantService;
import ink.scotty.cdd.service.LikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Like)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:40:47
 */
@RestController
@RequestMapping("like")
public class LikeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LikeService likeService;
    @Resource
    private InstantService instantService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param like 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Like> page, Like like) {
        return success(this.likeService.page(page, new QueryWrapper<>(like)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.likeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param like 实体对象
     * @return 新增结果
     */
//    @PostMapping
//    public R<?> insert(@RequestBody Like like) {
//        return success(this.likeService.save(like));
//    }

    /**
     * 修改数据
     *
     * @param like 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Like like) {
        return success(this.likeService.updateById(like));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.likeService.removeByIds(idList));
    }

    @PostMapping
    public R<?> like(@RequestBody Like like) {
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        wrapper
                .eq("instant_id", like.getInstantId())
                .eq("user_id", like.getUserId());
        int cnt = this.likeService.count(wrapper);
        if(cnt == 0){   //如果没有 未点赞 添加
            boolean flag = this.likeService.save(like);
            if(flag == false){   //添加失败
                return success(false);
            }else{  //添加成功
                //根据 instant_id 修改 instant 表中的 like_number
                Instant instant = this.instantService.getById(like.getInstantId());
                instant.setLikeNumber(instant.getLikeNumber() + 1);
                this.instantService.updateById(instant);
                return success(true);
            }
        }else{ //已点赞  删除
            boolean flag = this.likeService.remove(wrapper);
            if(flag == false){
                return success(false);
            }else{
                Instant instant = this.instantService.getById(like.getInstantId());
                instant.setLikeNumber(instant.getLikeNumber() - 1);
                this.instantService.updateById(instant);
                return success(true);
            }
        }
    }
}