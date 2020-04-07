package ink.scotty.cdd.controller;

import ink.scotty.cdd.pojo.User;
import ink.scotty.cdd.service.UserService;
import ink.scotty.cdd.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JsonResponse jsonResponse;


    @GetMapping("/login")
    public JsonResponse login(@RequestParam("account") String account,
                      @RequestParam("password") String password) {

        User user = userService.login(account, password);
        if (user == null) {
            return jsonResponse.setError("Login fail");
        }
        return jsonResponse.setSuccess(user);
    }

    @DeleteMapping
    public JsonResponse deleteUser(@RequestBody Map<String, Integer> request) {
        int userId = request.get("userId");
        userService.deleteUser(userId);
        return jsonResponse.setSuccess("delete successfully");
    }

    @PostMapping("/register")
    public JsonResponse register(@RequestBody User user) {
        User newUser = userService.register(user);
        if (newUser == null) return jsonResponse.setError("register fail");
        return jsonResponse.setSuccess(newUser);
    }

    @PutMapping
    public JsonResponse updateUserInfo(@RequestBody User user) {
        userService.updateUserInfo(user);
//        dataMap.put("")
        return jsonResponse.setSuccess("update successfully");

    }
}
