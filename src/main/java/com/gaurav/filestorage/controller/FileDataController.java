package com.gaurav.filestorage.controller;

import com.gaurav.filestorage.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileDataController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/fileupload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadFile = fileStorageService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadFile);

    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName)  {
        byte[] file = fileStorageService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(file);

    }

}
