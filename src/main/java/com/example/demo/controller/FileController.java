package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<Long> uploadFile(@RequestParam("file") MultipartFile file) {
        // 模擬保存文件並生成 fileId
        System.out.println("Received file: " + file.getOriginalFilename());

        // 模擬生成 fileId（實際應存儲文件並返回 ID）
        Long fileId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        return ResponseEntity.ok(fileId);
    }
}
