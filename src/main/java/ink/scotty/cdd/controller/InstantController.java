package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.dto.InstantDTO;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.entity.Like;
import ink.scotty.cdd.entity.User;
import ink.scotty.cdd.service.InstantService;
import ink.scotty.cdd.service.LikeService;
import ink.scotty.cdd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Instant)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:40:38
 */
@RestController
@RequestMapping("instant")
public class InstantController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private InstantService instantService;
    @Resource
    private UserService userService;
    @Resource
    private LikeService likeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param instant 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R<?> selectAll(Page<Instant> page, Instant instant) {
//        return success(this.instantService.page(page, new QueryWrapper<>(instant)));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.instantService.getById(id));
    }

    /**
     * 1. 添加动态
     * @param instant 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Instant instant) {
        boolean flag = this.instantService.save(instant);
        if(flag == false){
            return success(false);
        }else{
            User user = this.userService.getById(instant.getUserId());
            user.setInstantNumber(user.getInstantNumber() + 1);
            this.userService.updateById(user);
            return success(true);
        }
    }

    /**
     * 修改数据
     *
     * @param instant 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Instant instant) {
        return success(this.instantService.updateById(instant));
    }

    /**
     * 2. 删除动态
     * @param instantId 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("instant_id") Long instantId) {
        QueryWrapper<Instant> wrapper = new QueryWrapper<>();
        wrapper.eq("id", instantId);
        int cnt = this.instantService.count(wrapper);
        if(cnt == 0){
            return success(false);
        }else{
            Instant instant = this.instantService.getById(instantId);
            boolean flag = this.instantService.removeById(instantId);
            User user = this.userService.getById(instant.getUserId());
            user.setInstantNumber(user.getInstantNumber() - 1);
            this.userService.updateById(user);
            return success(true);
        }
    }


    /**
     * 3. 获取推荐动态列表
     * @param userId 当前用户id
     * @return
     */
    @GetMapping("hot")
    public R<?> getHotInstantList(@RequestParam("user_id") Long userId){
        QueryWrapper<Instant> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<Instant> list = this.instantService.list(wrapper);
        List<InstantDTO> res = new ArrayList<>();
        for(Instant i: list){
            InstantDTO instantDTO = getInstantDTO(i, userId);
            res.add(instantDTO);
        }
        return success(res);
    }

    /**
     * 4. 获取关注的人的动态列表
     * @param userId 登陆用户id
     * @param followId  关注用户id
     * @return
     */
    @GetMapping("follow")
    public R<?> getFollowInstantList(@RequestParam("user_id") Long userId, @RequestParam("follow_id") Long followId) {
        QueryWrapper<Instant> wrapper = new QueryWrapper<>();
        wrapper
                .eq("user_id", followId)
                .orderByDesc("create_time");
        List<Instant> list = this.instantService.list(wrapper);
        List<InstantDTO> res = new ArrayList<>();
        for(Instant i: list){
            InstantDTO instantDTO = getInstantDTO(i, userId);
            res.add(instantDTO);
        }
        return success(res);
    }

    private InstantDTO getInstantDTO(Instant instant, Long userId){
        InstantDTO instantDTO = new InstantDTO();
        instantDTO.setInstant(instant);

        QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("user_id", userId)
                .eq("instant_id", instant.getId());
        int cnt = this.likeService.count(likeQueryWrapper);
        if(cnt == 0){
            instantDTO.setStatus(0);
        }else{
            instantDTO.setStatus(1);
        }
        return instantDTO;
    }
}