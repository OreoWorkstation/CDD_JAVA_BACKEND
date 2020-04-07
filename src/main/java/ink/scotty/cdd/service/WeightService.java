package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.Weight;

import java.util.List;

public interface WeightService {

    // 添加体重信息
    Weight addWeight(Weight weight);

    // 删除体重信息
    int deleteWeight(int weightId, int petId);

    // 获取所有体重信息
    List<Weight> getAllWeights(int petId);

    // 更新体重信息
    void updateWeight(Weight weight);
}
