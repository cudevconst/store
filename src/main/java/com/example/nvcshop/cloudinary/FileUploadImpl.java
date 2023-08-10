package com.example.nvcshop.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileUploadImpl {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}

