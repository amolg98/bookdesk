package com.amolgupta.bookdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.amolgupta.bookdesk.entity.Booking;
import com.amolgupta.bookdesk.respository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    } 

    public Booking findById(String id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return null;
        }
        return booking;
    }

    public List<Booking> findByRoom(String roomId) {
        List<Booking> bookingList = bookingRepository.findByRoom(roomId);
        if (bookingList.isEmpty()) {
            return null;
        }
        return bookingList;
    }

    @Transactional
    public Booking create(Booking booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId());
        if (existingBooking != null) {

        }
        Booking currentBooking = bookingRepository.create(booking);
        return currentBooking;
    }

    @Transactional
    public Booking update(Booking booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId());
        if (existingBooking == null) {
            return bookingRepository.create(booking);
        }
        return bookingRepository.update(booking);
    }

    @Transactional
    public void delete(String id) {
        Booking existingBooking = bookingRepository.findById(id);
        if (existingBooking == null) {
            
        }
        bookingRepository.delete(id);
    }

}
