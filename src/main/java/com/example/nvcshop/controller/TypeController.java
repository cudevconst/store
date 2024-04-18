package com.example.nvcshop.controller;

import com.example.nvcshop.dto.request.TypeRequest;
import com.example.nvcshop.dto.response.TypeResponse;
import com.example.nvcshop.service.TypeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/type-product")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllType(){
        List<TypeResponse> typeResponseList = typeService.getAllTypeProduct();
        return ResponseEntity.status(200).body(typeResponseList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createType(@RequestParam("type") String type){
        TypeRequest typeRequest = TypeRequest.builder()
                .nameType(type)
                .build();
        TypeResponse typeResponse = typeService.createTypeProduct(typeRequest);
        return ResponseEntity.status(200).body(typeResponse);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateType(@RequestParam("typeId") @Parameter(example = "074b3e49-b83e-4d3a-9040-65f06965a63f") String typeId, @RequestBody TypeRequest typeRequest){
        TypeResponse typeResponse = typeService.updateTypeProduct(typeId, typeRequest);
        return ResponseEntity.status(200).body(typeResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType(@PathVariable("id") String id){
        Map<String, Object> map = new HashMap<>();
        if(typeService.deleteTypeProduct(id)){
            map.put("status", "200");
            map.put("message", "Delete type success");
            return ResponseEntity.status(200).body(map);
        }
        else{
            map.put("status", "400");
            map.put("message", "Not found type ID");
            return ResponseEntity.status(400).body(map);
        }
    }
}
