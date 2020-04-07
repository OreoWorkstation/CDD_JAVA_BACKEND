package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.Diary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiaryMapper {

    // 添加日记
    @Insert("INSERT INTO diary (pet_id, title, content, create_time, update_time)" +
            " values (#{petId}, #{title}, #{content}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addDiary(Diary diary);

    // 删除日记
    @Delete("DELETE FROM diary WHERE id = #{diaryId}")
    void deleteDiary(int diaryId);

    // 根据宠物Id获取所有日记
    @Select("SELECT * FROM diary WHERE pet_id = #{petId} ORDER BY create_time DESC")
    @Results(id = "diaryMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "petId", column = "pet_id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Diary> getAllDiaries(int petId);

    //根据日记ID获取宠物ID
    @Select("SELECT pet_id FROM diary WHERE id = #{diaryId}")
    Integer getPetIdByDiaryId(int diaryId);

    // 更新日记
    @Update("UPDATE diary SET " +
            "pet_id = #{petId}," +
            "title = #{title}," +
            "content = #{content}," +
            "create_time = #{createTime}," +
            "update_time = #{updateTime} " +
            " WHERE id = #{id}")
    void updateDiary(Diary diary);
}
