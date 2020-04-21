package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.PhotoDao;
import ink.scotty.cdd.entity.Photo;
import ink.scotty.cdd.service.PhotoService;
import org.springframework.stereotype.Service;

/**
 * (Photo)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:41:05
 */
@Service("photoService")
public class PhotoServiceImpl extends ServiceImpl<PhotoDao, Photo> implements PhotoService {

}