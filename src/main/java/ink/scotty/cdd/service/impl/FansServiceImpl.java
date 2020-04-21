package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.FansDao;
import ink.scotty.cdd.entity.Fans;
import ink.scotty.cdd.service.FansService;
import org.springframework.stereotype.Service;

/**
 * (Fans)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:19
 */
@Service("fansService")
public class FansServiceImpl extends ServiceImpl<FansDao, Fans> implements FansService {

}