package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.SimilarDao;
import ink.scotty.cdd.entity.Similar;
import ink.scotty.cdd.service.SimilarService;
import org.springframework.stereotype.Service;

/**
 * (Similar)表服务实现类
 *
 * @author kai
 * @since 2020-06-03 21:44:24
 */
@Service("similarService")
public class SimilarServiceImpl extends ServiceImpl<SimilarDao, Similar> implements SimilarService {

}