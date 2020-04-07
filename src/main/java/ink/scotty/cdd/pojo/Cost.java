package ink.scotty.cdd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private int id;
    private int petId;
    private String content;
    private double costValue;
    private String createTime;
}
