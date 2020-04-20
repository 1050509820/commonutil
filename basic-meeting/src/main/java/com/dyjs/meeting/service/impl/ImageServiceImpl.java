package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.Image;
import com.dyjs.meeting.mapper.ImageMapper;
import com.dyjs.meeting.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void insertImage(Image image) {
        imageMapper.insertSelective(image);
    }

    @Override
    public void delImage(int id) {
        imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Image> getImage() {
        return imageMapper.getAllImage();
    }
}
