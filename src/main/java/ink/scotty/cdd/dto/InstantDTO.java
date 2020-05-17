package ink.scotty.cdd.dto;

import ink.scotty.cdd.entity.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装动态，添加本次请求的用户是否对该动态进行了点赞状态
 * @author Scott
 * @date 2020/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstantDTO {

    // 发布者姓名
    private String nickname;
    // 发布者头像
    private String avatar;
    // 0: 未点赞 1：已点赞
    private Integer status;
    // Instant 类
    private Instant instant;

}
