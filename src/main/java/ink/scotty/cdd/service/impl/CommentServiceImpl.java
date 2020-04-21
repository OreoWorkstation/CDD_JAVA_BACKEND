package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.CommentDao;
import ink.scotty.cdd.entity.Comment;
import ink.scotty.cdd.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * (Comment)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:39:48
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

}