package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.PetMapper;
import ink.scotty.cdd.mapper.UserMapper;
import ink.scotty.cdd.pojo.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetMapper petMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Pet addPet(Pet pet) {
        userMapper.updateUserPetNumber(pet.getUserId(), 1);
        petMapper.addPet(pet);
        return pet;
    }

    @Override
    public int deletePet(int petId, int userId) {
        int _userId = petMapper.getUserIdByPetId(petId);
        if (_userId != userId) return -1;
        userMapper.updateUserPetNumber(userId, -1);
        petMapper.deletePet(petId);
        return 1;
    }

    @Override
    public List<Pet> getAllPets(int userId) {
        return petMapper.getAllPets(userId);
    }

    @Override
    public void updatePetInfo(Pet pet) {
        petMapper.updatePetInfo(pet.getId(), pet.getNickName(),
                pet.getGender(), pet.getSpecies(), pet.getIntroduction());
    }

    @Override
    public void updatePetAvatar(int petId, String avatar) {
        petMapper.updatePetAvatar(petId, avatar);
    }
}
