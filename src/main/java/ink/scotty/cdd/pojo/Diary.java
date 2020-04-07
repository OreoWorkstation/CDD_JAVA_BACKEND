package ink.scotty.cdd.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary {
    private int id;
    private int petId;
    private String title;
    private String content;
    private String createTime;
    private String updateTime;
}
