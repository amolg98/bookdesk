package com.amolgupta.bookdesk.service;

import java.util.List;
import com.amolgupta.bookdesk.entity.Booking;

public interface BookingService {
    
    List<Booking> findAll(); 
    Booking findById(String id);
    List<Booking> findByRoom(String roomId);

    Booking create(Booking booking);
    Booking update(Booking booking);
    void delete(String id);

}
