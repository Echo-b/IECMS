package cn.edu.swjtu.service;

import cn.edu.swjtu.result.ResponseData;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseData fileHandle(MultipartFile file);
}
