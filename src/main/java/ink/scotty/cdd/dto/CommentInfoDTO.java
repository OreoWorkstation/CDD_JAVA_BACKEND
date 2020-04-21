package ink.scotty.cdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 封装评论
 * 评论中要显示评论者的头像，昵称，时间，被评论者的昵称以及评论内容
 * 被评论者昵称为空，代表对动态评论
 * 不为空，代表回复某人
 * @author Scott
 * @date 2020/4/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfoDTO {

    // 评论者ID
    private Long userId;
    // 评论者头像
    private String userAvatar;
    // 评论者昵称
    private String userNickname;
    // 被评论者ID
    private Long parentId;
    // 被评论者昵称，为空代表评论动态
    private String parentNickname;
    // 评论内容
    private String content;
    // 评论时间
    private Date createTime;
}
