package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.Pet;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetMapper {

    // 添加宠物，并返回宠物Id到Pet中
    @Insert("INSERT INTO pet (user_id, nick_name, gender, species, avatar, introduction, create_time, diary_number, " +
            "weight, total_cost, photo_number)" +
            "values(#{userId}, #{nickName}, #{gender}, #{species}, #{avatar}, #{introduction}, #{createTime}, #{diaryNumber}, " +
            "#{weight}, #{totalCost}, #{photoNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addPet(Pet pet);

    // 删除宠物
    @Delete("DELETE FROM pet WHERE id = #{petId}")
    void deletePet(int petId);

    // 根据用户Id获取宠物列表
    @Select("SELECT * FROM pet WHERE user_id = #{userId}")
    @Results(id = "petMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "nickName", column = "nick_name"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "species", column = "species"),
        @Result(property = "avatar", column = "avatar"),
        @Result(property = "introduction", column = "introduction"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "diaryNumber", column = "diary_number"),
        @Result(property = "weight", column = "weight"),
        @Result(property = "totalCost", column = "total_cost"),
        @Result(property = "photoNumber", column = "photo_number")
    })
    List<Pet> getAllPets(int userId);

    // 通过宠物Id获取UserId
    @Select("SELECT user_id FROM pet WHERE id = #{petId}")
    Integer getUserIdByPetId(int petId);

    // 更新宠物信息, 昵称，性别，物种
    @Update("UPDATE pet SET nick_name = #{nickName}, gender = #{gender}, species = #{species}, " +
            "introduction = #{introduction} WHERE id = #{petId}")
    void updatePetInfo(int petId, String nickName, int gender, String species, String introduction);

    // 更换宠物头像
    @Update("UPDATE pet SET avatar = #{avatar} WHERE id = #{petId}")
    void updatePetAvatar(int petId, String avatar);


    // 更新宠物日记数量, 加/减number
    @Update("UPDATE pet SET diary_number = diary_number + #{number} WHERE id = #{petId}")
    void updatePetDiaryNumber(int petId, int number);

    // 更新宠物体重
    @Update("UPDATE pet SET weight = #{weight} WHERE id = #{petId}")
    void updatePetWeight(int petId, double weight);

    // 更新宠物相片数量
    @Update("UPDATE pet SET photo_number = photo_number + #{number} WHERE id = #{petId}")
    void updatePetPhotoNumber(int petId, int number);

    // 更新宠物总消费 加上number
    @Update("UPDATE pet SET total_cost = total_cost + #{number} WHERE id = #{petId}")
    void updatePetCost(int petId, double number);
}
