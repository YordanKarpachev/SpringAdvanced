package com.example.dbImages.dbImages.service;


import com.example.dbImages.dbImages.model.FileDownloadModel;
import com.example.dbImages.dbImages.model.FileEntity;
import com.example.dbImages.dbImages.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public long saveFile(MultipartFile file) throws IOException {


        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        return fileRepository.save(fileEntity).getId();
    }

    public Optional<FileDownloadModel> getFileById(long fileId) {
        FileEntity fileEntity = this.fileRepository.findById(fileId).orElseThrow();

        return Optional.of(new FileDownloadModel(fileEntity.getData(),
                fileEntity.getContentType(),
                fileEntity.getFileName()));

    }
}
