package com.study.onlineexam.controller;

import com.study.onlineexam.utils.QiniuUtils;
import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    @ApiOperation("上传图片")
    public ResponseVo upload(@RequestParam("image")MultipartFile file){
        //原始文件名称  比如aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename,"." );
        boolean upload = qiniuUtils.upload(file, fileName);
        System.out.println(QiniuUtils.url + fileName);
        if (upload){
            return ResponseVo.result(200,"上传成功",QiniuUtils.url + fileName);
        }
        return ResponseVo.result(-200,"上传失败");
    }
}
