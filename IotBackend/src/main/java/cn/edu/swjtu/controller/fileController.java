package cn.edu.swjtu.controller;

import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class fileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseData UserAvatarUpload(@RequestParam("file") MultipartFile file){
        return fileService.fileHandle(file);
    }
}
