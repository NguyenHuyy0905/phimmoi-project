package com.phimmoi.techwizapi.controller;

import com.cloudinary.Cloudinary;
import com.phimmoi.techwizapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
public class UploadController {
    @Autowired
    Cloudinary cloudinary;

    @PostMapping("/image")
    public ResponseEntity<ApiResponse.Response> test(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return ApiResponse.ok(cloudinary.uploader().upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
                .get("url").toString());
    }

    @PostMapping("/video")
    public ResponseEntity<ApiResponse.Response> uploadVideo(@RequestParam("video") MultipartFile multipartFile) throws IOException {
        Map<String, Object> options = Map.of(
                "resource_type", "video",  // Chỉ định rằng đây là video
                "public_id", UUID.randomUUID().toString()
        );

        return ApiResponse.ok(cloudinary.uploader()
                .upload(multipartFile.getBytes(), options)
                .get("url")
                .toString());
    }
}
