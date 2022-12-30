package com.gaurav.filestorage.repository;

import com.gaurav.filestorage.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileStorageRepo extends JpaRepository<FileData,Long> {
    @Override
    Optional<FileData> findById(Long fileId);

    Optional<FileData> findByName(String fileName);
}
