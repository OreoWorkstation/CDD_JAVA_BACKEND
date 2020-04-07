package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.Diary;
import ink.scotty.cdd.service.DiaryService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    @Autowired
    JsonResponse jsonResponse;

    @PostMapping
    public JsonResponse addDiary(@RequestBody Diary diary) {
        Diary newDiary = diaryService.addDiary(diary);
        if (newDiary == null) return jsonResponse.setError("Add error");
        return jsonResponse.setSuccess(newDiary);
    }

    @DeleteMapping
    public JsonResponse deleteDiary(@RequestBody Map<String, Integer> request) {
        int diaryId = request.get("diaryId");
        int petId = request.get("petId");
        int status = diaryService.deleteDiary(diaryId, petId);
        if (status < 0) return jsonResponse.setError("delete error");
        return jsonResponse.setSuccess("delete successfully");
    }

    @GetMapping
    public JsonResponse getAllDiaries(@RequestParam("pet_id") int petId) {
        return jsonResponse.setSuccess(diaryService.getAllDiaries(petId));
    }

    @PutMapping
    public JsonResponse updateDiary(@RequestBody Diary diary) {
        diaryService.updateDiary(diary);
        return jsonResponse.setSuccess("update successfully");
    }
}
