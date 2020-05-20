package ink.scotty.cdd.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.scotty.cdd.entity.Article;
import ink.scotty.cdd.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author Scott
 * @Kai
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

//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param article 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R<?> selectAll(Page<Article> page, Article article) {
//        return success(this.articleService.page(page, new QueryWrapper<>(article)));
//    }

    /**
     * 4. 获取单个文章
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.articleService.getById(id));
    }

    /**
     * 5. 用户按照分类获取文章列表
     * @param category
     * @return
     */
    @GetMapping
    public R<?> selectByCategory(@RequestParam("category") int category){
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("category", category);
        List<Article> articles = this.articleService.list(articleQueryWrapper);
        return success(articles);
    }


    @GetMapping("category")
    public R<?> selectByExpert(@RequestParam("expert_id") int expert_id){
        return success("1");
    }

    /**
     * 新增数据
     *
     * @param article 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Article article) {
        return success(this.articleService.save(article));
    }

    /**
     * 修改数据
     *
     * @param article 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Article article) {
        return success(this.articleService.updateById(article));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.articleService.removeByIds(idList));
    }
}