package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.dto.CommentInfoDTO;
import ink.scotty.cdd.entity.Comment;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.entity.User;
import ink.scotty.cdd.service.CommentService;
import ink.scotty.cdd.service.InstantService;
import ink.scotty.cdd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Comment)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-04-21 22:39:48
 */
@RestController
@RequestMapping("comment")
public class CommentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;
    @Resource
    private InstantService instantService;
    @Resource
    private UserService userService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param comment 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R<?> selectAll(Page<Comment> page, Comment comment) {
//        return success(this.commentService.page(page, new QueryWrapper<>(comment)));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.commentService.getById(id));
    }

    /**
     * 1. 新增评论
     * @param comment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Comment comment) {
        boolean flag = this.commentService.save(comment);
        if(flag == false){
            return success(false);
        }else{
            Instant instant = this.instantService.getById(comment.getInstantId());
            instant.setCommentNumber(instant.getCommentNumber() + 1);
            this.instantService.updateById(instant);
            return success(true);
        }
    }

    /**
     * 修改数据
     *
     * @param comment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Comment comment) {
        return success(this.commentService.updateById(comment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.commentService.removeByIds(idList));
    }

    /**
     * 2. 获取评论
     * @param instantId
     * @return
     */
    @GetMapping
    public R<?> getCommentListByInstantId(@RequestParam("instant_id") Long instantId){
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("instant_id", instantId)
                .orderByDesc("create_time");
        List<Comment> comments = this.commentService.list(wrapper);
        List<CommentInfoDTO> res = new ArrayList<>();

        for(Comment comment: comments){
            res.add(getCommentInfoDto(comment));
        }

        return success(res);
    }

    private CommentInfoDTO getCommentInfoDto(Comment comment){
        User user = this.userService.getById(comment.getUserId());
        User parent_user = null;
        if(comment.getParentId() != 0){
            parent_user= this.userService.getById(comment.getParentId());
        }
        CommentInfoDTO ret = new CommentInfoDTO();
        ret.setContent(comment.getContent());
        ret.setCreateTime(comment.getCreateTime());
        ret.setUserId(user.getId());
        ret.setUserAvatar(user.getAvatar());
        ret.setUserNickname(user.getNickname());
        if(comment.getParentId() != 0){
            ret.setParentId(parent_user.getId());
            ret.setParentNickname(parent_user.getNickname());
        }
        return ret;
    }
}