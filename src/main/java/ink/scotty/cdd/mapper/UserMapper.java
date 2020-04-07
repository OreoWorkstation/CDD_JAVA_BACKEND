package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    // 添加用户，并返回用户Id到User中
    @Insert("INSERT INTO user (account, u_password, nick_name, gender, address, avatar" +
            ", introduction, pet_number)" +
            " values (#{account}, #{password}, #{nickName}, #{gender}, #{address}, #{avatar}" +
            ", #{introduction}, #{petNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addUser(User user);

    // 删除用户
    @Delete("DELETE FROM user WHERE id = #{userId}")
    void deleteUser(int userId);

    // 根据用户账号返回用户信息
    @Select("SELECT * FROM user WHERE account = #{account}")
    @Results(id = "userMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "account", column = "account"),
        @Result(property = "password", column = "u_password"),
        @Result(property = "nickName", column = "nick_name"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "address", column = "address"),
        @Result(property = "avatar", column = "avatar"),
        @Result(property = "introduction", column = "introduction"),
        @Result(property = "petNumber", column = "pet_number")
    })
    User getUserByAccount(String account);

    // 更新用户信息
    @Update("UPDATE user SET account = #{account}, " +
            "u_password = #{password}, " +
            "nick_name = #{nickName}, " +
            "gender = #{gender}, " +
            "address = #{address}, " +
            "avatar = #{avatar}, " +
            "introduction = #{introduction}, " +
            "pet_number = #{petNumber} " +
            "WHERE id = #{id}")
    void updateUserInfo(User user);

    // 更新用户宠物数量，加或减
    // number值为1 或 -1
    @Update("UPDATE user SET pet_number = pet_number + #{number} WHERE id = #{userId}")
    void updateUserPetNumber(int userId, int number);
}
