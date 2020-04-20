package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.PetDao;
import ink.scotty.cdd.entity.Pet;
import ink.scotty.cdd.service.PetService;
import org.springframework.stereotype.Service;

/**
 * (Pet)表服务实现类
 *
 * @author Scott
 * @since 2020-04-20 18:52:07
 */
@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetDao, Pet> implements PetService {

}