package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.NewsDto;
import com.dyjs.meeting.mapper.NewsDtoMapper;
import com.dyjs.meeting.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDtoMapper newsDtoMapper;
    @Override
    public List<NewsDto> queryNews() {
        return newsDtoMapper.queryList();
    }
}
