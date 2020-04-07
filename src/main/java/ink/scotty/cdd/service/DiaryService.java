package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.Diary;

import java.util.List;

public interface DiaryService {

    // 添加日记
    Diary addDiary(Diary diary);

    // 删除日记
    int deleteDiary(int diaryId, int petId);

    // 获取所有日记
    List<Diary> getAllDiaries(int petId);

    // 修改日记信息
    void updateDiary(Diary diary);
}
