package com.amolgupta.bookdesk.respository;

import com.amolgupta.bookdesk.entity.Booking;
import java.util.List;

public interface BookingRepository {

    List<Booking> findAll(); 
    Booking findById(String id);
    List<Booking> findByRoom(String roomId);
    
    Booking create(Booking booking);
    Booking update(Booking booking);
    void delete(String id);

}
