package ink.scotty.cdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Scott
 * @date 2020/5/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    //主键
    private Long id;
    //文章标题
    private String title;
    //文章缩略图
    private String thumbnail;
    //文章内容
    private String content;
    //文章分类
    private Integer category;
    // 浏览值
    private Integer browseValue;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
