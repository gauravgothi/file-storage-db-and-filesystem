package com.gaurav.filestorage.repository;

import com.gaurav.filestorage.entity.FileData;
import com.gaurav.filestorage.entity.FileSystemStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileSystemStorageRepository extends JpaRepository<FileSystemStorage,Long> {


    Optional<FileSystemStorage> findByName(String fileName);
}
