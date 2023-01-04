package com.gaurav.filestorage.service;

import com.gaurav.filestorage.entity.FileData;
import com.gaurav.filestorage.entity.FileSystemStorage;
import com.gaurav.filestorage.repository.FileSystemStorageRepository;
import com.gaurav.filestorage.util.FileDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileSystemStorageService {

    @Autowired
    private FileSystemStorageRepository fileSystemStorageRepository;

    private final String FOLDER_PATH="C:/Users/HP/Desktop/MyFiles/";

    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();
        FileSystemStorage fileSystemStorage = fileSystemStorageRepository.save(FileSystemStorage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileSystemStorage != null)   {
            return "file uploaded successfully "+ fileSystemStorage.getFilePath();
        }
        return null;
    }

    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {
        Optional<FileSystemStorage> fileSystemStorage = fileSystemStorageRepository.findByName(fileName);
        String filePath = fileSystemStorage.get().getFilePath();
        byte[] file = Files.readAllBytes(new File(filePath).toPath());
        return file;
    }
}
