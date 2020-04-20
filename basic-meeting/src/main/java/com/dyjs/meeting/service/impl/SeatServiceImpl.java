package com.dyjs.meeting.service.impl;

import com.dyjs.meeting.dao.Seat;
import com.dyjs.meeting.mapper.SeatMapper;
import com.dyjs.meeting.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatMapper seatMapper;

    @Transactional
    @Override
    public boolean orderSeat(String tel, String seatNo,String name) throws Exception{
        String[] arr = seatNo.split(",");
        for(String s:arr){
            Seat seat = seatMapper.selectBySeatNo(s);
            if(seat!=null){
                throw new RuntimeException();
            }else{
                Seat seat1 = new Seat();
                seat1.setCreattime(new Date());
                seat1.setSeatno(s);
                seat1.setName(name);
                seat1.setTel(tel);
                try{
                    seatMapper.insertSelective(seat1);
                }catch (Exception e){
                    throw new RuntimeException();
                }
            }


        }
        return  true;
    }

    @Override
    public List<Seat> getSeat() {
        return seatMapper.getSeat();
    }

    @Override
    public List<Seat> getSeatBytel(String tel) {
        return seatMapper.selectByTel(tel);
    }

    @Override
    public int delOrder(Seat seat) {
        return seatMapper.delByDto(seat);
    }
}
