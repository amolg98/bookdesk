package com.amolgupta.bookdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.amolgupta.bookdesk.entity.Booking;
import com.amolgupta.bookdesk.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/booking")
public class BookingController {
    
    @Autowired
    BookingService bookingService;

    @RequestMapping(method=RequestMethod.GET,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> findAll() {
        return bookingService.findAll();
    } 
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}",
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Booking findById(@PathVariable("id") String id) {
        return bookingService.findById(id);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/{roomid}",
            produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> findByRoom(@PathVariable("roomid") String roomId) {
        return bookingService.findByRoom(roomId);
    }
 
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Booking create(@RequestBody Booking booking) {
        return bookingService.create(booking);
    }
    
    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Booking update(Booking booking) {
        return bookingService.update(booking);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        bookingService.delete(id);
    }
    
}
