package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.Cost;

import java.util.List;

public interface CostService {

    // 添加消费信息
    Cost addCost(Cost cost);

    // 删除消费信息
    int deleteCost(int costId, int petId);

    // 根据宠物Id获取所有消费信息
    List<Cost> getAllCosts(int petId);

    // 更新消费信息
    int updateCost(Cost cost);

}
