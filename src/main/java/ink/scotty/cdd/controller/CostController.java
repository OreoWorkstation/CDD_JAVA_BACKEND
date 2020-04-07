package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.Cost;
import ink.scotty.cdd.service.CostService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/cost")
public class CostController {
    @Autowired
    CostService costService;

    @Autowired
    JsonResponse jsonResponse;

    @PostMapping
    public JsonResponse addCost(@RequestBody Cost cost) {
        Cost newCost = costService.addCost(cost);
        if (newCost == null) return jsonResponse.setError("Add fail");
        return jsonResponse.setSuccess(cost);
    }

    @DeleteMapping
    public JsonResponse deleteCost(@RequestBody Map<String, Integer> request) {
        int costId = request.get("costId");
        int petId = request.get("petId");
        int status = costService.deleteCost(costId, petId);
        if (status > 0) return jsonResponse.setSuccess("delete successfully");
        return jsonResponse.setError("delete error");
    }

    @GetMapping
    public JsonResponse getAllCosts(@RequestParam("pet_id") int petId) {
        return jsonResponse.setSuccess(costService.getAllCosts(petId));
    }

    @PutMapping
    public JsonResponse updateCost(@RequestBody Cost cost) {
        int status = costService.updateCost(cost);
        if (status > 0) return jsonResponse.setSuccess("update successfully");
        return jsonResponse.setError("update error");
    }
}
