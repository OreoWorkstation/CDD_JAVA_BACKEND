package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.BrowseDao;
import ink.scotty.cdd.entity.Browse;
import ink.scotty.cdd.service.BrowseService;
import org.springframework.stereotype.Service;

/**
 * (Browse)表服务实现类
 *
 * @author Scott
 * @since 2020-05-19 15:44:07
 */
@Service("browseService")
public class BrowseServiceImpl extends ServiceImpl<BrowseDao, Browse> implements BrowseService {

}