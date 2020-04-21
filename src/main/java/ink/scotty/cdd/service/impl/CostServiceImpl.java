package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.CostDao;
import ink.scotty.cdd.entity.Cost;
import ink.scotty.cdd.service.CostService;
import org.springframework.stereotype.Service;

/**
 * (Cost)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:01
 */
@Service("costService")
public class CostServiceImpl extends ServiceImpl<CostDao, Cost> implements CostService {

}