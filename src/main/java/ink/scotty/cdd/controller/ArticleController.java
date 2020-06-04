package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.dto.ArticleDTO;
import ink.scotty.cdd.entity.Article;
import ink.scotty.cdd.entity.Browse;
import ink.scotty.cdd.entity.Expert;
import ink.scotty.cdd.entity.Recommend;
import ink.scotty.cdd.service.ArticleService;
import ink.scotty.cdd.service.BrowseService;
import ink.scotty.cdd.service.ExpertService;
import ink.scotty.cdd.service.RecommendService;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author Scott
 * @author Kai
 * @since 2020-05-19 15:43:27
 */
@RestController
@RequestMapping("article")
public class ArticleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;
    @Resource
    private ExpertService expertService;
    @Resource
    private RecommendService recommendService;
    @Resource
    private BrowseService browseService;

    /**
     * 1. 添加文章
     * @param article 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Article article) {
        return success(this.articleService.save(article));
    }

    /**
     * 2. 删除文章
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.articleService.removeByIds(idList));
    }

    /**
     * 3. 宠物专家获取文章列表
     * @param expert_id
     * @return
     */
    @GetMapping
    public R<?> selectArticleByExpert(@RequestParam("expert_id") Long expert_id){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("expert_id",expert_id);
        return getR(queryWrapper);
    }

    private R<?> getR(QueryWrapper<Article> queryWrapper) {
        List<Article> articles = this.articleService.list(queryWrapper);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for(Article article: articles){
            articleDTOS.add(getArticleDTO(article.getId(), (long) 0));
        }
        return success(articleDTOS);
    }

    /**
     * 4. 获取单个文章
     * @param article_id 主键
     * @return 单条数据
     */
    @GetMapping("{article_id}")
    public R<?> selectOne(@PathVariable Long article_id) {
        return success(getArticleDTO(article_id, (long) 0));
    }

    /**
     * 5. 用户按照分类获取文章列表
     * @param category
     * @param user_id
     * @return
     */
    @GetMapping("/category/{category}")
    public R<?> selectByCategory(@PathVariable int category, @RequestParam("user_id") Long user_id){
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("category", category);
        List<Article> articleList = new ArrayList<>();
        articleList = this.articleService.list(articleQueryWrapper);

        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for(Article article: articleList){
            articleDTOList.add(getArticleDTO(article.getId(), user_id));
        }
        return success(articleDTOList);
    }

    /**
     * 6. 用户获取推荐文章列表
     * @param user_id
     * @return
     */
    @GetMapping("recommend")
    public R<?> selectRecommendList(@RequestParam("user_id") Long user_id){
        //1. 按照user_id查询Recommend中对该用户推荐的文章的article_id
        QueryWrapper<Recommend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        List<Recommend> recommendList = new ArrayList<>();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        List<Long> articleids = new ArrayList<>(); // 记录文章id的list
        int cnt = this.recommendService.count(queryWrapper);
        if(cnt != 0){
            //返回DTOList
            recommendList = this.recommendService.list(queryWrapper);
            for(Recommend recommend: recommendList){
                articleDTOS.add(getArticleDTO(recommend.getArticleId(), user_id));
                articleids.add(recommend.getArticleId());
            }
        }
        articleids.add((long) 0);
        //若推荐文章少于10篇，则从文章序列中添加若干篇文章
        if(articleDTOS.size() < 10){
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.notIn("id", articleids)
                    .orderByDesc("create_time");
            List<Article> articleList = this.articleService.list(articleQueryWrapper);
            int len = 0;
            if(articleList.size() >= 10){
                len = 10;
            }else{
                len = articleList.size();
            }
            for(int i = 0 ; i < len; ++ i){
                Article article = articleList.get(i);
                articleDTOS.add(getArticleDTO(article.getId(), user_id));
            }
        }
        return success(articleDTOS);
    }

    /**
     * 7. 用户获取轮播文章
     * @return
     */
    @GetMapping("hot")
    public R<?> selectHotArticle(@RequestParam("user_id") Long user_id){
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("create_time");
        List<Article> articles = new ArrayList<>();
        articles = this.articleService.list(wrapper);

        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for(int i = 0; i < 4; ++ i){
            System.out.println(i);
            articleDTOS.add(getArticleDTO(articles.get(i).getId(), user_id));
        }

        return success(articleDTOS);
    }

    /**
     * 8. 修改文章
     * @param article 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Article article) {
        return success(this.articleService.updateById(article));
    }



    /**
     * 自定义函数：按照文章id获取ArticleDTO
     * @param article_id
     * @return
     */
    private ArticleDTO getArticleDTO(Long article_id, Long userId){
        //判断文章是否存在
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", article_id);
        int cnt = this.articleService.count(queryWrapper);
        if(cnt == 0){
            return null;
        }
        //获取文章
        Article article = this.articleService.getById(article_id);

        //判断Expert是否存在
        QueryWrapper<Expert> expertQueryWrapper = new QueryWrapper<>();
        expertQueryWrapper.eq("id", article.getExpertId());
        int cnt2 = this.expertService.count(expertQueryWrapper);

        Expert expert = null;
        ArticleDTO articleDTO = new ArticleDTO();
        //如果expert存在
        if(cnt2 != 0){
            expert = this.expertService.getById(article.getExpertId());
            articleDTO.setExpertName(expert.getNickname());
            articleDTO.setExpertAvatar(expert.getAvatar());
        }

        articleDTO.setExpertId(article.getExpertId());
        articleDTO.setId(article.getId());
        articleDTO.setThumbnail(article.getThumbnail());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setCategory(article.getCategory());
        if(userId == 0)
            articleDTO.setBrowseValue(0);
        else{
            QueryWrapper<Browse> browseQueryWrapper = new QueryWrapper<>();
            browseQueryWrapper.eq("user_id", userId)
                    .eq("article_id", article_id);
            int browse_val = this.browseService.count(browseQueryWrapper);
            if(browse_val == 0)  // 用户没有看过
                articleDTO.setBrowseValue(0);
            else{
                Browse browse = this.browseService.getOne(browseQueryWrapper);
                articleDTO.setBrowseValue(browse.getBrowseValue());
            }
        }
        articleDTO.setUpdateTime(article.getUpdateTime());
        articleDTO.setCreateTime(article.getCreateTime());

        return articleDTO;
    }
}