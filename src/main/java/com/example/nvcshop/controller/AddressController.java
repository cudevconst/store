package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.AddressRequest;
import com.example.nvcshop.dto.response.AddressResponse;
import com.example.nvcshop.entity.Address;
import com.example.nvcshop.mapper.AddressMapper;
import com.example.nvcshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user")
    private ResponseEntity<?> findAddressByUserId(@RequestParam("userId") String userId){
        List<AddressResponse> addressResponseList = addressService.getAllAddressById(userId);
        if(addressResponseList != null){
            return ResponseEntity.status(200).body(addressResponseList);
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Not found");
            return ResponseEntity.status(400).body(map);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findAddressById(@PathVariable("id") String id){
        AddressResponse addressResponse = addressService.getAddressById(id);
        if(addressResponse != null){
            return ResponseEntity.status(200).body(addressResponse);
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Not found address ID");
            return ResponseEntity.status(400).body(map);
        }
    }

    @PostMapping("/create")
    private ResponseEntity<?> createNewAddress(@RequestParam("userId") String userId,
                                               @RequestParam("phoneNumber") String phoneNumber,
                                               @RequestParam("city") String city,
                                               @RequestParam("district") String district,
                                               @RequestParam("commune") String commune,
                                               @RequestParam("details") String details){
        AddressRequest addressRequest = AddressRequest.builder()
                .phoneNumber(phoneNumber)
                .city(city)
                .district(district)
                .commune(commune)
                .details(details)
                .build();
        AddressResponse addressResponse = addressService.createAddress(userId, addressRequest);
        return ResponseEntity.status(200).body(addressResponse);
    }

    @PatchMapping("/update")
    private ResponseEntity<?> updateAddress(@RequestParam("addressId") String userId,
                                            @RequestParam("phoneNumber") String phoneNumber,
                                            @RequestParam("city") String city,
                                            @RequestParam("district") String district,
                                            @RequestParam("commune") String commune,
                                            @RequestParam("details") String details){
        AddressRequest addressRequest = AddressRequest.builder()
                .phoneNumber(phoneNumber)
                .city(city)
                .district(district)
                .commune(commune)
                .details(details)
                .build();
        AddressResponse addressResponse = addressService.updateAddress(userId, addressRequest);
        return ResponseEntity.status(200).body(addressResponse);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteAddress(@PathVariable("id") String id){
        Map<String, Object> map = new HashMap<>();
        if(addressService.deleteAddress(id)){
            map.put("status", "200");
            map.put("message", "Delete address success");
            return ResponseEntity.status(200).body(map);

        }
        else{
            map.put("status", "400");
            map.put("message", "Not found address ID");
            return ResponseEntity.status(400).body(map);
        }
    }
}
