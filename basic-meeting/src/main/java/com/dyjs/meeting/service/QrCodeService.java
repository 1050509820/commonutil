package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.QrCodeDto;

public interface QrCodeService {
    void insert(QrCodeDto qrCodeDto);
    QrCodeDto selectByOpenid(String  openid);
    void deleteByTel(String tel);
    QrCodeDto selectByTel(String tel);

}
