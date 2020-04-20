package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.CodeDto;
import com.dyjs.meeting.mapper.CodeDtoMapper;
import com.dyjs.meeting.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeDtoMapper codeDtoMapper;
    @Override
    public void insert(CodeDto codeDto) {
        codeDtoMapper.insert(codeDto);
    }

    @Override
    public CodeDto getCode(String tel) {
        return codeDtoMapper.selectByTel(tel);
    }

    @Override
    public void update(CodeDto codeDto) {
        codeDtoMapper.updateByPrimaryKeySelective(codeDto);
    }
}
