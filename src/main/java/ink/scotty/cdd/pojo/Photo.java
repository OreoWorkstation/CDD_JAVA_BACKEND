package ink.scotty.cdd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    private int id;
    private int petId;
    private String photoValue;
    private String description;
    private String createTime;
}
