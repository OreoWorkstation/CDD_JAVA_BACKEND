package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.FollowDao;
import ink.scotty.cdd.entity.Follow;
import ink.scotty.cdd.service.FollowService;
import org.springframework.stereotype.Service;

/**
 * (Follow)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:30
 */
@Service("followService")
public class FollowServiceImpl extends ServiceImpl<FollowDao, Follow> implements FollowService {

}