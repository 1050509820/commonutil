package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.CodeDto;

public interface CodeService {
     void insert(CodeDto codeDto);
     CodeDto getCode(String tel);
     void update(CodeDto codeDto);
}
