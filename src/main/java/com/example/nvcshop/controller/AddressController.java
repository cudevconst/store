package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.AddressRequest;
import com.example.nvcshop.dto.response.AddressResponse;
import com.example.nvcshop.entity.Address;
import com.example.nvcshop.mapper.AddressMapper;
import com.example.nvcshop.service.AddressService;
import io.swagger.v3.oas.annotations.Parameter;
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
    private ResponseEntity<?> findAddressByUserId(@RequestParam("userId") @Parameter(example = "272888d4-1b7a-4ced-b34c-062e8c97319e") String userId){
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
    private ResponseEntity<?> findAddressById(@PathVariable("id") @Parameter(example = "97eb3dc2-67a5-42a1-b388-db76ad29a03f") String id){
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
    private ResponseEntity<?> createNewAddress(@RequestParam("userId") @Parameter(example = "272888d4-1b7a-4ced-b34c-062e8c97319e") String userId ,
                                               @RequestParam("phoneNumber") @Parameter(example = "0999999999") String phoneNumber,
                                               @RequestParam("city") @Parameter(example = "Ha Noi") String city,
                                               @RequestParam("district") @Parameter(example = "Thuong Tin") String district,
                                               @RequestParam("commune") @Parameter(example = "Van Tao") String commune,
                                               @RequestParam("details") @Parameter(example = "Noi Thon") String details){
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
    private ResponseEntity<?> updateAddress(@RequestParam("addressId") @Parameter(example = "97eb3dc2-67a5-42a1-b388-db76ad29a03f") String userId,
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
