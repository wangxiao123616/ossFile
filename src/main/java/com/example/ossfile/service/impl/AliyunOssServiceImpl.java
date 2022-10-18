package com.example.ossfile.service.impl;

import com.example.ossfile.service.AliyunOssService;
import com.example.ossfile.tool.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("aliyunOssService")
public class AliyunOssServiceImpl implements AliyunOssService {
    @Resource
    private FileUploadUtil fileUploadUtil;

    @Override
    public Map<String, Object> upload(MultipartFile file, String fileName){
        //定义一个目录（这里的目录可根据自己实际情况自己拼接定义）
        String path = "a";   //我这里就随便定义一个a文件夹
        //拼接，这里将路径和文件名拼接在了一起
        fileName = path + "/" +fileName;
        Map<String, Object> map =new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.fileUploadUtil.upload(file, fileName));
        return map;
    }
}