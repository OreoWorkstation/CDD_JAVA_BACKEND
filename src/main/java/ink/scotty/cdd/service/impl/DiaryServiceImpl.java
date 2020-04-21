package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.DiaryDao;
import ink.scotty.cdd.entity.Diary;
import ink.scotty.cdd.service.DiaryService;
import org.springframework.stereotype.Service;

/**
 * (Diary)表服务实现类
 *
 * @author Scott
 * @since 2020-04-21 22:40:09
 */
@Service("diaryService")
public class DiaryServiceImpl extends ServiceImpl<DiaryDao, Diary> implements DiaryService {

}