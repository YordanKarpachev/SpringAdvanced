package com.example.dbImages.dbImages.repository;

import com.example.dbImages.dbImages.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<FileEntity, Long> {
}
