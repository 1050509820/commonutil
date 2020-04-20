package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.QrCodeDto;
import com.dyjs.meeting.mapper.QrCodeDtoMapper;
import com.dyjs.meeting.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Autowired
    QrCodeDtoMapper qrCodeDtoMapper;

    @Override
    public void insert(QrCodeDto qrCodeDto) {
        qrCodeDtoMapper.insertSelective(qrCodeDto);
    }

    @Override
    public QrCodeDto selectByOpenid(String  openid) {
        return qrCodeDtoMapper.selectByPrimaryKey(openid);
    }

    @Override
    public void deleteByTel(String tel) {
        qrCodeDtoMapper.deleteByTel(tel);
    }

    @Override
    public QrCodeDto selectByTel(String tel) {
        return qrCodeDtoMapper.selectByTel(tel);
    }
}
