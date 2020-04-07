package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.Photo;
import ink.scotty.cdd.service.PhotoService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @Autowired
    JsonResponse jsonResponse;

    @PostMapping
    public JsonResponse addPhoto(@RequestBody Photo photo) {
        Photo newPhoto = photoService.addPhoto(photo);
        if (newPhoto == null) return jsonResponse.setError("add error");
        return jsonResponse.setSuccess(newPhoto);
    }

    @DeleteMapping
    public JsonResponse deletePhoto(@RequestBody Map<String, Integer> request) {
        int photoId = request.get("photoId");
        int petId = request.get("petId");
        int status = photoService.deletePhoto(photoId, petId);
        if (status < 0) return jsonResponse.setError("delete error");
        return jsonResponse.setSuccess("delete successfully");
    }

    @GetMapping
    public JsonResponse getAllPhotos(@RequestParam("pet_id") int petId) {
        return jsonResponse.setSuccess(photoService.getAllPhotos(petId));
    }

    @PutMapping
    public JsonResponse updatePhoto(@RequestBody Photo photo) {
        photoService.updatePhoto(photo);
        return jsonResponse.setSuccess("update successfully");
    }

}
