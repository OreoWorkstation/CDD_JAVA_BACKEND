package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.LikeDao;
import ink.scotty.cdd.entity.Like;
import ink.scotty.cdd.service.LikeService;
import org.springframework.stereotype.Service;

/**
 * (Like)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:47
 */
@Service("likeService")
public class LikeServiceImpl extends ServiceImpl<LikeDao, Like> implements LikeService {

}