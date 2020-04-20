package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.Image;

import java.util.List;

public interface ImageService {
    void insertImage(Image image);
    void delImage(int  id);
    List<Image> getImage();
}
