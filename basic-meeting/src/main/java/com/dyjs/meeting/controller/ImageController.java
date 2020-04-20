package com.dyjs.meeting.controller;


import com.dyjs.meeting.dao.Image;
import com.dyjs.meeting.service.ImageService;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("basic-meeting")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/fileUpload")
    public BaseResponse fileUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "/data/img/"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponseUtil.error(500,"上传失败");
        }
        String filename = "/img/" + fileName;
        Image image =new Image();
        image.setName(fileName.substring(0,fileName.lastIndexOf(".")));
        image.setUrl(filename);
        imageService.insertImage(image);
        return BaseResponseUtil.success("上传成功");
    }

    @PostMapping("/delImage")
    public BaseResponse delImage(String ids){
        try {
            String[] arr = ids.split(",");
            if(arr.length==0){
                return BaseResponseUtil.error(500,"参数错误");
            }else{
                for(String s :arr){
                    int id = Integer.parseInt(s);
                    imageService.delImage(id);
                }
                return BaseResponseUtil.success();
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponseUtil.error(500,"参数错误");
        }
    }

    @PostMapping("getImages")
    public BaseResponse getImages(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List list = imageService.getImage();
        PageInfo pageInfo = new PageInfo(list);
        return BaseResponseUtil.success(pageInfo);
    }

}
