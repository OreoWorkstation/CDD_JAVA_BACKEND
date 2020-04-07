package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.Weight;
import ink.scotty.cdd.service.WeightService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/weight")
public class WeightController {
    @Autowired
    WeightService weightService;

    @Autowired
    JsonResponse jsonResponse;

    @PostMapping
    public JsonResponse addWeight(@RequestBody Weight weight) {
        Weight newWeight = weightService.addWeight(weight);
        if (newWeight == null) return jsonResponse.setError("add error");
        return jsonResponse.setSuccess(newWeight);
    }

    @DeleteMapping
    public JsonResponse deleteWeight(@RequestBody Map<String, Integer> request) {
        int weightId = request.get("weightId");
        int petId = request.get("petId");
        int status = weightService.deleteWeight(weightId, petId);
        if (status < 0) return jsonResponse.setError("delete error");
        return jsonResponse.setSuccess("delete successfully");
    }

    @GetMapping
    public JsonResponse getAllWeights(@RequestParam("pet_id") int petId) {
        return jsonResponse.setSuccess(weightService.getAllWeights(petId));
    }

    @PutMapping
    public JsonResponse updateWeight(@RequestBody Weight weight) {
        weightService.updateWeight(weight);
        return jsonResponse.setSuccess("update successfully");
    }
}
