package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.dto.InstantDTO;
import ink.scotty.cdd.dto.UserInfoDTO;
import ink.scotty.cdd.dto.UserZoneDTO;
import ink.scotty.cdd.entity.Follow;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.entity.Like;
import ink.scotty.cdd.entity.User;
import ink.scotty.cdd.service.FollowService;
import ink.scotty.cdd.service.InstantService;
import ink.scotty.cdd.service.LikeService;
import ink.scotty.cdd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:41:13
 *
 */
@RestController
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private FollowService followService;
    @Resource
    private InstantService instantService;
    @Resource
    private LikeService likeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping("user")
    public R<?> selectAll(Page<User> page, User user) {
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     *
     * 3.获取用户信息 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("user/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("user")
    public R<?> insert(@RequestBody User user) {
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     * 4. 更新用户信息 前端设置id和account不改变
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping("user")
    public R<?> update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("user")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userService.removeByIds(idList));
    }

    /**
     * 1.用户登陆
     * @param user
     * @return
     */
    @PostMapping("login")
    public R<?> login(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("account", user.getAccount())
                .eq("password", user.getPassword());
        int cnt = this.userService.count(wrapper);
        if(cnt != 0){
            User res = this.userService.getOne(wrapper);
            return success(res.getId());
        }else{
            return failed("用户名与密码不匹配");
        }
    }

    /**
     * 2.注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public R<?> register(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("account", user.getAccount());
        int cnt = this.userService.count(wrapper);
        if(cnt != 0){
            return failed("账号已存在");
        }else{
            return this.insert(user);
        }
    }

    /**
     * 5. 获取关注的人列表
     */
    @GetMapping("user/{user_id}/follow")
    public R<?> getFollowList(@PathVariable("user_id") Serializable UserId){
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", UserId);
        List<Follow> follows = this.followService.list(wrapper);
        List<UserInfoDTO> ret = new ArrayList<>();
        for(Follow follow: follows){
            User user = this.userService.getById(follow.getFollowedId());
            ret.add(getUserInfoDto(user));
        }
        return success(ret);
    }

    /**
     * 6. 获取获取粉丝列表
     * @param UserId
     * @return
     */
    @GetMapping("user/{user_id}/fans")
    public R<?> getFansList(@PathVariable("user_id") Serializable UserId){
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("followed_id", UserId);
        List<Follow> follows = this.followService.list(wrapper);
        List<UserInfoDTO> ret = new ArrayList<>();
        for(Follow follow: follows){
            User user = this.userService.getById(follow.getUserId());
            ret.add(getUserInfoDto(user));
        }
        return success(ret);
    }

    private UserInfoDTO getUserInfoDto(User user){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(user.getId());
        userInfoDTO.setAvatar(user.getAvatar());
        userInfoDTO.setGender(user.getGender());
        userInfoDTO.setNickname(user.getNickname());
        userInfoDTO.setIntroduction(user.getIntroduction());
        return userInfoDTO;
    }

    /**
     *
     * @param UserId
     * @return
     */
    @GetMapping("user/{user_id}/zone")
    public R<?> getUserZoneById(@PathVariable("user_id") Long UserId){
        UserZoneDTO userZoneDTO = new UserZoneDTO();
        User user = this.userService.getById(UserId);
        userZoneDTO.setUserId(user.getId());
        userZoneDTO.setAddress(user.getAddress());
        userZoneDTO.setAvatar(user.getAvatar());
        userZoneDTO.setGender(user.getGender());
        userZoneDTO.setNickname(user.getNickname());


        QueryWrapper<Instant> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<Instant> list = this.instantService.list(wrapper);
        List<InstantDTO> res = new ArrayList<>();
        for(Instant i: list){
            InstantDTO instantDTO = getInstantDTO(i, UserId);
            res.add(instantDTO);
        }
        userZoneDTO.setInstantDTOList(res);
        return success(userZoneDTO);
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

//    // 用户动态列表
//    List<InstantDTO> instantDTOList;