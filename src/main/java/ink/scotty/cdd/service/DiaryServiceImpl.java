package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.DiaryMapper;
import ink.scotty.cdd.mapper.PetMapper;
import ink.scotty.cdd.pojo.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    DiaryMapper diaryMapper;
    @Autowired
    PetMapper petMapper;

    @Override
    public Diary addDiary(Diary diary) {
        petMapper.updatePetDiaryNumber(diary.getPetId(), 1);
        diaryMapper.addDiary(diary);
        return diary;
    }

    @Override
    public int deleteDiary(int diaryId, int petId) {
        int _petId = diaryMapper.getPetIdByDiaryId(diaryId);
        if (_petId != petId) return -1;
        petMapper.updatePetDiaryNumber(petId, -1);
        diaryMapper.deleteDiary(diaryId);
        return 1;
    }

    @Override
    public List<Diary> getAllDiaries(int petId) {
        return diaryMapper.getAllDiaries(petId);
    }

    @Override
    public void updateDiary(Diary diary) {
        diaryMapper.updateDiary(diary);
    }
}
