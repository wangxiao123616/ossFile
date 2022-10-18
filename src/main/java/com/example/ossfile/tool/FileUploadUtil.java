package com.example.ossfile.tool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@Data
public class FileUploadUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String upload(MultipartFile file, String fileName){
        // 写入文件
        try {
            //获取上传的输入流
            InputStream inputStream = file.getInputStream();

            //设置访问地址是默认是预览图片
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");

            // 创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

                /*
                调用oss上传到阿里云对象存储oss----Bucket存储空间
                第一个参数 bucket名称
                第二个参数 文件名称
                第三个参数 输入流
                第四个参数 文件类型
                 */
            ossClient.putObject(bucketName, fileName, inputStream, meta);

            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            //ossClient.deleteObject(bucketName, "a/");

            // 关闭OSSClient。
            ossClient.shutdown();

            //上传成功后获取文件路径
            String url = "https://" + bucketName + "." + endpoint + "/"+ fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}