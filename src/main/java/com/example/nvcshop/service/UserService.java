package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.UserRequest;
import com.example.nvcshop.dto.response.UserResponse;
import com.example.nvcshop.entity.Cart;
import com.example.nvcshop.entity.User;
import com.example.nvcshop.repository.CartRepository;
import com.example.nvcshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;
    public UserResponse checkAccount(String email, String password){
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Cart> list = cartRepository.findAllByUserId(user.getId());
            List<String> cartIdList = new ArrayList<>();
            for(Cart cart : list){
                cartIdList.add(cart.getId());
            }
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .createAt(user.getCreateAt())
                    .lastModify(user.getLastModify())
                    .cartId(cartIdList)
                    .build();
            return userResponse;
        }
        else{
            return null;
        }
    }
    public UserResponse getUserById(String userId){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Cart> list = cartRepository.findAllByUserId(userId);
        List<String> cartIdList = new ArrayList<>();
        for(Cart cart : list){
            cartIdList.add(cart.getId());
        }
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .cartId(cartIdList)
                    .createAt(user.getCreateAt())
                    .lastModify(user.getLastModify())
                    .build();
            return userResponse;
        }
        else{
            return null;
        }
    }
    public Boolean existsUserByEmail(String email){
        if(userRepository.existsByEmail(email)){
            return false;
        }
        else {
            return true;
        }
    }
    @Transactional
    public ResponseEntity<?> saveUser(UserRequest userRequest) {
        Map<String, Object> map = new HashMap<>();
        if(existsUserByEmail(userRequest.getEmail())){
            User user = User.builder()
                    .email(userRequest.getEmail())
                    .password(userRequest.getPassword())
                    .fullName(userRequest.getFullName())
                    .role("user")
                    .build();
            userRepository.save(user);
            Cart cart = Cart.builder()
                    .user(user)
                    .build();
            cartRepository.save(cart);
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .createAt(user.getCreateAt())
                    .lastModify(user.getLastModify())
                    .build();
            map.put("status", "200");
            map.put("message", "Register Success");
            map.put("user", userResponse);
            return ResponseEntity.ok(map);
        }
        else {
            map.put("status", "400");
            map.put("message", "Account exists");
            return ResponseEntity.status(400).body(map);
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(String userId, UserRequest userRequest){
        Map<String, Object> map = new HashMap<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
           User user = userOptional.get();
           user.setFullName(userRequest.getFullName());
           userRepository.save(user);
           UserResponse userResponse = UserResponse.builder()
                   .id(user.getId())
                   .email(user.getEmail())
                   .fullName(user.getFullName())
                   .createAt(user.getCreateAt())
                   .lastModify(user.getLastModify())
                   .build();
           map.put("status", "200");
           map.put("message", "Update account success");
           map.put("user", userResponse);
           return ResponseEntity.ok(map);
        }
        else{
            map.put("status", "400");
            map.put("message", "Update account fail");
            return ResponseEntity.badRequest().body(map);
        }
    }

    @Transactional
    public ResponseEntity<?> changePassword(String userId, String password){
        Map<String, Object> map = new HashMap<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
            map.put("status", "200");
            map.put("message", "Change password success");
            return ResponseEntity.ok(map);
        }
        else{
            map.put("status", "400");
            map.put("message", "Change password fail");
            return ResponseEntity.badRequest().body(map);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteUser(String userId){
        Map<String, Object> map = new HashMap<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            userRepository.deleteById(userId);
            map.put("status", "200");
            map.put("message", "Delete account success");
            return ResponseEntity.ok(map);
        }
        else{
            map.put("status", "400");
            map.put("message", "Delete account fail");
            return ResponseEntity.badRequest().body(map);
        }
    }


}