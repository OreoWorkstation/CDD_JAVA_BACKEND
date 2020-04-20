package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.PetMapper;
import ink.scotty.cdd.mapper.WeightMapper;
import ink.scotty.cdd.pojo.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {
    @Autowired
    WeightMapper weightMapper;
    @Autowired
    PetMapper petMapper;

    @Override
    public Weight addWeight(Weight weight) {
        petMapper.updatePetWeight(weight.getPetId(), weight.getWeightValue());
        weightMapper.addWeight(weight);
        return weight;
    }

    @Override
    public int deleteWeight(int weightId, int petId) {
        int _petId = weightMapper.getPetIdByWeightId(weightId);
        if (_petId != petId) return -1;

        // 删除相应体重信息
        weightMapper.deleteWeight(weightId);
        // 获取此时最新体重的ID
        int latestWeightId = weightMapper.getLatestWeightId(petId);
        // 说明删除的是最新的体重，需要更新宠物显示的体重信息
        if (weightId != latestWeightId) {
            petMapper.updatePetWeight(petId, weightMapper.getWeightValue(latestWeightId));
        }
        return 1;
    }

    @Override
    public List<Weight> getAllWeights(int petId) {
        return weightMapper.getAllWeights(petId);
    }

    @Override
    public void updateWeight(Weight weight) {
        // 获取最新体重的ID
        weightMapper.updateWeight(weight);
        int latestWeightId = weightMapper.getLatestWeightId(weight.getPetId());
//        if (weight.getId() != latestWeightId) {
            petMapper.updatePetWeight(weight.getPetId(), weightMapper.getWeightValue(latestWeightId));
//        }
    }
}
