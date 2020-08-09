package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.WeightDao;
import ink.scotty.cdd.entity.Weight;
import ink.scotty.cdd.service.WeightService;
import org.springframework.stereotype.Service;

/**
 * (Weight)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:41:21
 */
@Service("weightService")
public class WeightServiceImpl extends ServiceImpl<WeightDao, Weight> implements WeightService {

}