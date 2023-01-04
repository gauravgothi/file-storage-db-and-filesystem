package com.gaurav.filestorage.controller;

import com.gaurav.filestorage.entity.FileSystemStorage;
import com.gaurav.filestorage.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file-system")
public class FileSystemStorageController {

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @PostMapping("/fileupload")
    public ResponseEntity<?> uploadFileToFileSystem(MultipartFile file) throws IOException {
        String uploadFile = fileSystemStorageService.uploadFileToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadFile);

    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFileFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] file = fileSystemStorageService.downloadFileFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(file);

    }
}
