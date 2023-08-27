package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.UserRequest;
import com.example.nvcshop.dto.response.UserResponse;
import com.example.nvcshop.repository.UserRepository;
import com.example.nvcshop.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signInUser(@RequestParam("email") String email, @RequestParam("password") String password ){
        UserResponse userResponse = userService.checkAccount(email, password);
        if(userResponse != null){
            return ResponseEntity.status(200).body(userResponse);
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Email or password don't correct");
            return ResponseEntity.status(400).body(map);
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getUserById(@RequestParam("id") @Parameter(example = "1233445") String userId){
        UserResponse user = userService.getUserById(userId);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Account not exists");
            return ResponseEntity.status(400).body(map);
        }

    }
    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("fullname") String fullName){
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .fullName(fullName)
                .build();
        return userService.saveUser(userRequest);

    }
    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam("id") String userId,
                                            @RequestParam("password") String newPassword){
        return userService.changePassword(userId, newPassword);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestParam("id") String userId,
                                           @RequestParam("fullname") String fullName){
        UserRequest userRequest = UserRequest.builder()
                .fullName(fullName)
                .build();
        return userService.updateUser(userId, userRequest);
    }
}
