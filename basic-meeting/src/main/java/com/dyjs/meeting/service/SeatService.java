package com.dyjs.meeting.service;

import com.dyjs.meeting.dao.Seat;

import java.util.List;

public interface SeatService {
    public boolean orderSeat(String tel,String seatNo,String name) throws Exception;
    List<Seat> getSeat();

    List<Seat> getSeatBytel(String tel);

    int delOrder(Seat seat);
}
