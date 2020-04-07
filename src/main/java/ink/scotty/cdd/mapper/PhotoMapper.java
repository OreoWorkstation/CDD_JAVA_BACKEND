package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.Photo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoMapper {

    // 添加相片
    @Insert("INSERT INTO photo (pet_id, photo_value, description, create_time)" +
            " values (#{petId}, #{photoValue}, #{description}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addPhoto(Photo photo);

    // 删除相片
    @Delete("DELETE FROM photo WHERE id = #{photoId}")
    void deletePhoto(int photoId);

    // 根据宠物ID获取所有相片
    @Select("SELECT * FROM photo WHERE pet_id = #{petId} ORDER BY create_time DESC")
    @Results(id = "photoMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "petId", column = "pet_id"),
        @Result(property = "photoValue", column = "photo_value"),
        @Result(property = "description", column = "description"),
        @Result(property = "createTime", column = "create_time")
    })
    List<Photo> getAllPhotos(int petId);

    // 根据相片ID获取宠物ID
    @Select("SELECT pet_id FROM photo WHERE id = #{photoId}")
    Integer getPetIdByPhotoID(int photoId);

    // 更新相片信息: 描述信息
    @Update("UPDATE photo SET photo_value = #{photoValue}, description = #{description} " +
            "WHERE id = #{id}")
    void updatePhoto(Photo photo);
}
