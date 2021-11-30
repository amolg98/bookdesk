package com.amolgupta.bookdesk.controller;

import java.util.List;

import com.amolgupta.bookdesk.entity.Booking;
import com.amolgupta.bookdesk.entity.Room;
import com.amolgupta.bookdesk.service.RoomService;

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
@RequestMapping(value = "/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Room findById(@PathVariable("id") String roomId) {
        System.out.println("In findById of Room " + roomId);
        Room room = roomService.findById(roomId);
        System.out.println(room.getName());
        return room;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/date",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> findAllRoomByDate(@RequestBody Booking booking) {
        System.out.println("Variables are: " + booking.getStartBookTime() + " " + booking.getStopBookTime());
        return roomService.findAllByDate(booking.getStartBookTime(), booking.getStopBookTime());
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Room room) {
        roomService.create(room);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Room update(@RequestBody Room room) {
        return roomService.update(room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        System.out.println("In room controller delete " + id);
        roomService.delete(id);
    }
}
