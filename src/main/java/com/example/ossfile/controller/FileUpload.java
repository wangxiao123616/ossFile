package com.example.ossfile.controller;

import com.example.ossfile.service.impl.AliyunOssServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("oss")
public class FileUpload {

        @Resource
        private AliyunOssServiceImpl aliyunOssServiceImpl;

        @RequestMapping(value = "fileoss", method = RequestMethod.POST)
        public Map<String, Object> uploadOssFile(MultipartFile file, String fileName) {
            return this.aliyunOssServiceImpl.upload(file, fileName);
        }
    }