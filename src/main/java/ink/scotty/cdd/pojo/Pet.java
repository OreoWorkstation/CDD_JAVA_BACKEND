package ink.scotty.cdd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private int id;
    private int userId;
    private String nickName;
    private int gender;
    private String species;
    private String avatar;
    private String introduction;
    private String createTime;
    private int diaryNumber;
    private double weight;
    private double totalCost;
    private int photoNumber;

}
