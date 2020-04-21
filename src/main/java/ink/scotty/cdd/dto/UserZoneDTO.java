package ink.scotty.cdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户空间信息
 * @author Scott
 * @date 2020/4/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserZoneDTO {

    // 用户ID
    private Long userId;
    // 用户昵称
    private String nickname;
    // 用户性别
    private Integer gender;
    // 用户头像
    private String avatar;
    // 用户地址
    private String address;
    // 用户动态列表
    List<InstantDTO> instantDTOList;
}
