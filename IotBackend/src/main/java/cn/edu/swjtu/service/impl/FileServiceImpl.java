package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${images.path}")
    private String basePath;
    @Override
    public ResponseData fileHandle(MultipartFile file) {
        Path syspath = Path.of(System.getProperty("user.dir")).getParent();
        String dirpath = syspath.toString().replace("\\", "/");
        String originalFilename = file.getOriginalFilename();//获取文件名称
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀
        String filename= UUID.randomUUID()+suffix;//重新生成文件名
        dirpath += basePath;

        File dir = new File(dirpath);
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            // store tmp file to appoint filepath
            file.transferTo(new File(dirpath + filename));
        } catch (IOException e){
            e.printStackTrace();
        }
        return ResponseData.success("图片上传成功").data("filename",filename);
    }
}
