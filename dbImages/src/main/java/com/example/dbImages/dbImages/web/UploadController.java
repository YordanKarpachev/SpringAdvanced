package com.example.dbImages.dbImages.web;

import com.example.dbImages.dbImages.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UploadController {

    private FileService fileService;

    @Autowired
    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
public String upload(){
        return "upload";
    }



    @PostMapping("/upload")
    public String upload(FileUploadModel uploadModel) throws IOException {
        fileService.saveFile(uploadModel.getImg());
    return "redirect:/show/" +         fileService.saveFile(uploadModel.getImg());
}

}
