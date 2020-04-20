package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.UserDto;

public interface UserService {
    UserDto selectByUsername(String name);
}
