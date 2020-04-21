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

    // Instant 类
    private Instant instant;
    // 0: 未点赞 1：已点赞
    private Integer status;
}
