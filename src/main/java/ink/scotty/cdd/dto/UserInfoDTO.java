package ink.scotty.cdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关注的人或者粉丝列表信息
 *
 * @author Scott
 * @date 2020/4/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    // 用户ID
    private Long userId;
    // 用户昵称
    private String nickname;
    // 用户头像
    private String avatar;
    // 用户性别 0：男生 1：女生
    private Integer gender;
    // 用户个签
    private String introduction;
}
