package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.Weight;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WeightMapper {

    // 添加体重信息
    @Insert("INSERT INTO weight (pet_id, weight_value, create_time)" +
            " values (#{petId}, #{weightValue}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addWeight(Weight weight);

    // 删除体重信息
    @Delete("DELETE FROM weight WHERE id = #{weightId}")
    void deleteWeight(int weightId);

    // 根据宠物Id获取所有体重信息
    @Select("SELECT * FROM weight WHERE pet_id = #{petId} ORDER BY create_time DESC")
    @Results(id = "weightMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "petId", column = "pet_id"),
        @Result(property = "weightValue", column = "weight_value"),
        @Result(property = "createTime", column = "create_time")
    })
    List<Weight> getAllWeights(int petId);

    // 根据体重Id获取宠物ID
    @Select("SELECT pet_id FROM weight WHERE id = #{weightId}")
    Integer getPetIdByWeightId(int weightId);

    // 获取宠物最新体重的id
    @Select("SELECT id FROM weight WHERE create_time = " +
            "(SELECT max(create_time) FROM  weight WHERE pet_id = #{petId})")
    Integer getLatestWeightId(int petId);

    // 根据体重ID获取体重值
    @Select("SELECT weight_value FROM weight WHERE id = #{weightId}")
    Double getWeightValue(int weightId);

    // 更新体重信息
    @Update("UPDATE weight SET weight_value = #{weightValue}, create_time = #{createTime} " +
            "WHERE id = #{id}")
    void updateWeight(Weight weight);
}
