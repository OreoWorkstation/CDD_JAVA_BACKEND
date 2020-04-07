package ink.scotty.cdd.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String account;
    private String password;
    private String nickName;
    private int gender;
    private String address;
    private String avatar;
    private String introduction;
    private int petNumber;
}
