package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.Pet;
import ink.scotty.cdd.service.PetService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    JsonResponse jsonResponse;

    @PostMapping
    public JsonResponse addPet(@RequestBody Pet pet) {
        Pet newPet = petService.addPet(pet);
        if (newPet == null) return jsonResponse.setError("add pet fail");
        return jsonResponse.setSuccess(newPet);
    }

    @DeleteMapping
    public JsonResponse deletePet(@RequestBody Map<String, Integer> request) {
        int petId = request.get("petId");
        int userId = request.get("userId");
        int status = petService.deletePet(petId, userId);
        if (status > 0) return jsonResponse.setSuccess("delete successfully");
        return jsonResponse.setError("delete fail");
}

    @GetMapping
    public JsonResponse getAllPets(@RequestParam("user_id") int userId) {
        return jsonResponse.setSuccess(petService.getAllPets(userId));
    }

    @PutMapping
    public JsonResponse updatePetInfo(@RequestBody Pet pet) {
        petService.updatePetInfo(pet);
        return jsonResponse.setSuccess("update successfully");
    }

    @PutMapping("/avatar")
    public JsonResponse updatePetAvatar(@RequestBody Map<String, String> request) {
        int petId = Integer.parseInt(request.get("petId"));
        String avatar = request.get("avatar");
        petService.updatePetAvatar(petId, avatar);
        return jsonResponse.setSuccess("update avatar successfully");
    }
}
