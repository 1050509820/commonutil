package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> queryNews();

}
