package com.example.nvcshop.controller;

import com.example.nvcshop.cloudinary.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/cloud")
public class UploadFileController {
    @Autowired
    private FileUpload fileUpload;

    @PostMapping("/upload")
    private ResponseEntity<?> uploadImage(@RequestPart("image1") MultipartFile image1,
                                          @RequestPart("image2") MultipartFile image2,
                                          @RequestPart("image3") MultipartFile image3) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String image1Url = fileUpload.uploadFile(image1);
        String image2Url = fileUpload.uploadFile(image2);
        String image3Url = fileUpload.uploadFile(image3);
        map.put("image1", image1Url);
        map.put("image2", image2Url);
        map.put("image3", image3Url);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteImage(@PathVariable("id") String id) throws IOException {
        return ResponseEntity.ok(fileUpload.deleteFile(id));
    }
}
