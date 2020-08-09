package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.ExpertDao;
import ink.scotty.cdd.entity.Expert;
import ink.scotty.cdd.service.ExpertService;
import org.springframework.stereotype.Service;

/**
 * (Expert)表服务实现类
 *
 * @author Scott
 * @since 2020-05-19 15:43:02
 */
@Service("expertService")
public class ExpertServiceImpl extends ServiceImpl<ExpertDao, Expert> implements ExpertService {

}