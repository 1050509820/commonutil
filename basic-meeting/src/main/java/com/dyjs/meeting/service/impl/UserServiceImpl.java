package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.UserDto;
import com.dyjs.meeting.mapper.UserMapper;
import com.dyjs.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDaoMapper;

    @Override
    public UserDto selectByUsername(String name) {
        return userDaoMapper.selectByUsername(name);
    }
}
