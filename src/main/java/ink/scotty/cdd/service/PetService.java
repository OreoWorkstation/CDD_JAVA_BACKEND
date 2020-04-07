package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.Pet;

import java.util.List;

public interface PetService {

    // 添加宠物
    Pet addPet(Pet pet);

    // 删除宠物
    int deletePet(int petId, int userId);

    // 获取所有宠物
    List<Pet> getAllPets(int userId);

    // 修改宠物基本信息
    void updatePetInfo(Pet pet);

    // 更改宠物头像
    void updatePetAvatar(int petId, String avatar);

}
