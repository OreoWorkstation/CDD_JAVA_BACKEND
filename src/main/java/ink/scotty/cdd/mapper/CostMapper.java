package ink.scotty.cdd.mapper;

import ink.scotty.cdd.pojo.Cost;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CostMapper {

    // 添加消费信息
    @Insert("INSERT INTO cost (pet_id, content, cost_value, create_time)" +
            " values (#{petId}, #{content}, #{costValue}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addCost(Cost cost);

    // 删除消费信息
    @Delete("DELETE FROM cost WHERE id = #{costId}")
    void deleteCost(int costId);

    // 根据宠物Id获取所有消费信息
    @Select("SELECT * FROM cost WHERE pet_id = #{petId} ORDER BY create_time DESC")
    @Results(id = "costMap", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "petId", column = "pet_id"),
        @Result(property = "content", column = "content"),
        @Result(property = "costValue", column = "cost_value"),
        @Result(property = "createTime", column = "create_time")
    })
    List<Cost> getAllCosts(int petId);

    // 根据消费Id获取宠物Id
    @Select("SELECT pet_id FROM cost WHERE id = #{costId}")
    Integer getPetIdByCostId(int costId);

    // 根据消费Id获取消费金额
    @Select("SELECT cost_value FROM cost WHERE id = #{costId}")
    Double getCostById(int costId);

    // 更新消费信息
    @Update("UPDATE cost SET content = #{content}, cost_value = #{costValue}, create_time = #{createTime} " +
            "WHERE id = #{id}")
    void updateCost(Cost cost);
}
