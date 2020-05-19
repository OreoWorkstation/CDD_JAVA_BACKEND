package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.RecommendDao;
import ink.scotty.cdd.entity.Recommend;
import ink.scotty.cdd.service.RecommendService;
import org.springframework.stereotype.Service;

/**
 * (Recommend)表服务实现类
 *
 * @author Scott
 * @since 2020-05-19 15:44:29
 */
@Service("recommendService")
public class RecommendServiceImpl extends ServiceImpl<RecommendDao, Recommend> implements RecommendService {

}