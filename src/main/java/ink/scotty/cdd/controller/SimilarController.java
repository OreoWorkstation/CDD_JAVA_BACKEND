package ink.scotty.cdd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.dto.ArticleDTO;
import ink.scotty.cdd.entity.Article;
import ink.scotty.cdd.entity.Browse;
import ink.scotty.cdd.entity.Expert;
import ink.scotty.cdd.entity.Similar;
import ink.scotty.cdd.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Similar)表控制层
 * @author kai
 * @since 2020-06-03 21:44:27
 */
@RestController
@RequestMapping("similar")
public class SimilarController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SimilarService similarService;
    @Resource
    private BrowseService browseService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ExpertService expertService;



    @GetMapping
    public R<?> getSimilarArticle(@RequestParam("article_id") Long article_id, @RequestParam("user_id") Long user_id) {
        // 获取文章是那一个type
        QueryWrapper<Similar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article", article_id);
        Similar similar = this.similarService.getOne(queryWrapper);
        Long type = similar.getType();

        //获取除该文章的其它文章List
        QueryWrapper<Similar> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("type", type)
                .notIn("article", article_id);
        List<Similar> similarList = this.similarService.list(queryWrapper2);

        //最多推荐5篇相似的文章
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        int cnt = 0;
        for(Similar similar_i: similarList){
            if(cnt == 5)
                break;
            articleDTOS.add(getArticleDTO(article_id, user_id));
            cnt ++;
        }
        return success(articleDTOS);
    }

//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param similar 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R<?> selectAll(Page<Similar> page, Similar similar) {
//        return success(this.similarService.page(page, new QueryWrapper<>(similar)));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.similarService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param similar 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Similar similar) {
        return success(this.similarService.save(similar));
    }

    /**
     * 修改数据
     *
     * @param similar 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Similar similar) {
        return success(this.similarService.updateById(similar));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.similarService.removeByIds(idList));
    }


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