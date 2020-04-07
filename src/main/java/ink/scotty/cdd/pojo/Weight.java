package ink.scotty.cdd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weight {
    private int id;
    private int petId;
    private double weightValue;
    private String createTime;
}
