package ink.scotty.cdd;

import ink.scotty.cdd.mapper.*;
import ink.scotty.cdd.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CddApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PetMapper petMapper;

    @Autowired
    DiaryMapper diaryMapper;

    @Autowired
    WeightMapper weightMapper;

    @Autowired
    CostMapper costMapper;

    @Autowired
    PhotoMapper photoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testUserMapper() {
//        User user = new User(0, "user001", "user001", "nickname001",
//                0, "address", "avatar", "intro", 0);
//        System.out.println("user = " + user);
//
//        userMapper.addUser(user);
//
//        System.out.println("user = " + user);


//        User user = userMapper.getUserByAccount("user001");
//        System.out.println("user = " + user);

//        User user = new User(2, "user001", "user001", "nickname001",
//                0, "new address", "new avatar", "new intro", 0);
//        userMapper.updateUser(user);
//        System.out.println("user = " + user);

//        userMapper.updateUserPetNumber(2, -1);

    }

    @Test
    void testPetMapper() {
//        Pet pet = new Pet(0, 2, "dog03", 0, "dog", "a dog","2020-03-20", 0, 0, 0, 0);
//        System.out.println("pet = " + pet);
//        petMapper.addPet(pet);
//        System.out.println("pet = " + pet);

//        List<Pet> pets = petMapper.getAllPets(2);
//        System.out.println("pets = " + pets);

//        petMapper.updatePetCost(3, 20);
//        petMapper.updatePetDiaryNumber(1, 1);
//        petMapper.updatePetPhotoNumber(2, 1);
//        petMapper.updatePetWeight(2, 10);

//        petMapper.updatePetInfo(4, "dog03 new", 1, "new dog", "new dog");

//        petMapper.deletePet(1);
//
//        int result = petMapper.getUserIdByPetId(8);
//        System.out.println("result = " + result);

    }

    @Test
    void testDiaryMapper() {
//        Diary diary = new Diary(0,4, "diary001", "this is content", "2020-04-02", "2020-04-02");
//        System.out.println("diary = " + diary);
//        diaryMapper.addDiary(diary);
//        System.out.println("diary = " + diary);

//        List<Diary> diaries = diaryMapper.getAllDiaries(4);
//        System.out.println("diaries = " + diaries);
//        Diary diary = new Diary(2,4, "diary001", "this is new content", "2020-04-02", "2020-04-02");
//        diaryMapper.updateDiary(diary);
//        System.out.println("diary = " + diary);

//        diaryMapper.deleteDiary(1);

//        int res = diaryMapper.getPetIdByDiaryId(2);
//        System.out.println("res = " + res);
    }

    @Test
    void testWeightMapper() {
//        Weight weight = new Weight(0, 4, 23.0, "2020-01-04");
//        System.out.println("weight = " + weight);
//        weightMapper.addWeight(weight);
//        System.out.println("weight = " + weight);

//        List<Weight> weights = weightMapper.getAllWeights(4);
//        System.out.println("weights = " + weights);
//        Weight weight = new Weight(2, 4, 70.0, "2020-01-04");
//        weightMapper.updateWeight(weight);

//        weightMapper.deleteWeight(1);

//        int res = weightMapper.getPetIdByWeightId(2);
//        System.out.println("res = " + res);

//        int res = weightMapper.getLatestWeightId(4);
//        System.out.println("res = " + res);
//        double res = weightMapper.getWeightValue(3);
//        System.out.println("res = " + res);

//        int res = weightMapper.getLatestWeightId(9);
//        System.out.println("res = " + res);
    }


    @Test
    void testCostMapper() {
//        Cost cost = new Cost(0, 4, "cost03", 19.0, "2020-01-04");
//        costMapper.addCost(cost);
//        System.out.println("cost = " + cost);
//
//        List<Cost> costs = costMapper.getAllCosts(4);
//        System.out.println("costs = " + costs);

//        Cost cost = new Cost(3, 4, "cost03", 59.0, "2020-01-04");
//        costMapper.updateCost(cost);
//
//        costMapper.deleteCost(1);

//        int res = costMapper.getPetIdByCostId(2);
//        System.out.println("res = " + res);

    }

    @Test
    void testPhotoMapper() {
//        Photo photo = new Photo(0, 4, "url03", "a photo");
//        photoMapper.addPhoto(photo);
//        System.out.println("photo = " + photo);

//        List<Photo> photos = photoMapper.getAllPhotos(4);
//        System.out.println(photos);
//        Photo photo = new Photo(3, 4, "url03", "a new photo");
//        photoMapper.updatePhoto(photo);

//        photoMapper.deletePhoto(1);
//
//        int res = photoMapper.getPetIdByPhotoID(2);
//        System.out.println("res = " + res);
    }
}
