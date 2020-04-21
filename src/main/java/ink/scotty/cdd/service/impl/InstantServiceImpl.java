package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.InstantDao;
import ink.scotty.cdd.entity.Instant;
import ink.scotty.cdd.service.InstantService;
import org.springframework.stereotype.Service;

/**
 * (Instant)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:38
 */
@Service("instantService")
public class InstantServiceImpl extends ServiceImpl<InstantDao, Instant> implements InstantService {

}