package com.gaurav.filestorage.service;

import com.gaurav.filestorage.entity.FileData;
import com.gaurav.filestorage.repository.FileStorageRepo;
import com.gaurav.filestorage.util.FileDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    @Autowired
    private FileStorageRepo fileStorageRepo;

    public String uploadFile(MultipartFile file) throws IOException {
        FileData fileData = fileStorageRepo.save(FileData.builder()
                            .name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .fileData(FileDataUtils.compressFile(file.getBytes())).build());

        if (fileData != null)   {
            return "file uploaded successfully "+ file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadFile(String fileName) {
        Optional<FileData> dbFileData = fileStorageRepo.findByName(fileName);
        byte[] files = FileDataUtils.decompress(dbFileData.get().getFileData());
        return files;
    }

}
