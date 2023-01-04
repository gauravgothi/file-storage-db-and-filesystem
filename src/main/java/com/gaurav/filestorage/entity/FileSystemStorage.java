package com.gaurav.filestorage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file_system_storage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileSystemStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;
}
