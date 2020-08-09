package ink.scotty.cdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.scotty.cdd.dao.ArticleDao;
import ink.scotty.cdd.entity.Article;
import ink.scotty.cdd.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * (Article)表服务实现类
 *
 * @author Scott
 * @since 2020-05-19 15:43:27
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

}