package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.CostMapper;
import ink.scotty.cdd.mapper.PetMapper;
import ink.scotty.cdd.pojo.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    CostMapper costMapper;
    @Autowired
    PetMapper petMapper;

    @Override
    public Cost addCost(Cost cost) {
        petMapper.updatePetCost(cost.getPetId(), cost.getCostValue());
        costMapper.addCost(cost);
        return cost;
    }

    @Override
    public int deleteCost(int costId, int petId) {
        int _petId = costMapper.getPetIdByCostId(costId);
        if (_petId != petId) return -1;
        double oldValue = costMapper.getCostById(costId);
        petMapper.updatePetCost(petId, -oldValue);
        costMapper.deleteCost(costId);
        return 1;
    }

    @Override
    public List<Cost> getAllCosts(int petId) {
        return costMapper.getAllCosts(petId);
    }

    @Override
    public int updateCost(Cost cost) {
        int petId = costMapper.getPetIdByCostId(cost.getId());
        if (petId != cost.getPetId()) return -1;

        double oldValue = costMapper.getCostById(cost.getId());
        double delta = cost.getCostValue() - oldValue;
        petMapper.updatePetCost(petId, delta);
        costMapper.updateCost(cost);
        return 1;
    }
}
