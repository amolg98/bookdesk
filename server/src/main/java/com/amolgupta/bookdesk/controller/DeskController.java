package com.amolgupta.bookdesk.controller;

import java.util.ArrayList;
import java.util.List;
// import java.sql.Timestamp;

import com.amolgupta.bookdesk.entity.Desk;
import com.amolgupta.bookdesk.entity.Booking;
import com.amolgupta.bookdesk.service.DeskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/desk")
public class DeskController {
    
    @Autowired
    private DeskService deskService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Desk> findAll() {
        System.out.println("In findAll of Desk");
        return deskService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Desk findById(@PathVariable("id") String id) {
        Desk desk = deskService.findById(id);
        return desk;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/date",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Desk> findAllDeskByDate(@RequestBody Booking booking) {
        System.out.println("Variables are: " + booking.getStartBookTime() + " " + booking.getStopBookTime());
        List<Desk> allDesks = new ArrayList<>();
        allDesks = deskService.findAllByDate(booking.getStartBookTime(), booking.getStopBookTime());

        System.out.println(allDesks.toString());

        return allDesks;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Desk create(@RequestBody Desk desk) {
        System.out.println("In POST method of Desk!!");
        return deskService.create(desk);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Desk update(@RequestBody Desk desk) {
        return deskService.update(desk);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        deskService.delete(id);
    }
}
