package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Fans;
import ink.scotty.cdd.entity.Follow;
import ink.scotty.cdd.entity.User;
import ink.scotty.cdd.service.FansService;
import ink.scotty.cdd.service.FollowService;
import ink.scotty.cdd.service.UserService;
import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Follow)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:40:30
 */
@RestController
@RequestMapping("follow")
public class FollowController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private FollowService followService;
    @Resource
    private UserService userService;
    @Resource
    private FansService fansService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param follow 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<Follow> page, Follow follow) {
        return success(this.followService.page(page, new QueryWrapper<>(follow)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.followService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param follow 实体对象
     * @return 新增结果
     */
//    @PostMapping
//    public R<?> insert(@RequestBody Follow follow) {
//        return success(this.followService.save(follow));
//    }

    /**
     * 修改数据
     *
     * @param follow 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Follow follow) {
        return success(this.followService.updateById(follow));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.followService.removeByIds(idList));
    }

    /**
     * 关注
     * @param follow
     * @return
     */
    @PostMapping
    public R<?> Follow(@RequestBody Follow follow) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", follow.getUserId())
                .eq("followed_id", follow.getFollowedId());
        QueryWrapper<Fans> fansQueryWrapper = new QueryWrapper<>();
        fansQueryWrapper.eq("user_id", follow.getFollowedId())
                .eq("fans_id", follow.getUserId());

        int cnt = this.followService.count(queryWrapper);
        boolean flag, flag2;
        Fans fans = new Fans();
        fans.setUserId(follow.getFollowedId());
        fans.setFansId(follow.getUserId());

        if(cnt == 0){   //未关注
            flag = this.followService.save(follow);
            flag2 = this.fansService.save(fans);
        }else{  //已关注
            flag = this.followService.remove(queryWrapper);
            flag2 = this.fansService.remove(fansQueryWrapper);
        }
        int n = (cnt == 0 ? 1 : -1);
        if(flag == true && flag2 == true){
            User user = this.userService.getById(follow.getUserId()); //增加follow_number
            user.setFollowNumber(user.getFollowNumber() + n);
            this.userService.updateById(user);

            User followed = this.userService.getById(follow.getFollowedId());
            followed.setFansNumber(followed.getFansNumber() + n);
            this.userService.updateById(followed);
            return success(true);
        }else{
            return success(false);
        }
    }
}