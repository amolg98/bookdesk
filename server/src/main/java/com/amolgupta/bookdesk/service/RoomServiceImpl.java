package com.amolgupta.bookdesk.service;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
// import java.util.Date;

import com.amolgupta.bookdesk.entity.Desk;
import com.amolgupta.bookdesk.entity.Room;
import com.amolgupta.bookdesk.respository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp) {
        List<Room> allRooms = new ArrayList<>();
        allRooms = roomRepository.findAllByDate(startTimestamp, stopTimestamp);
        return allRooms;
    }

    public Room findById(String id) {
        Room existingRoom = roomRepository.findById(id);
        if (existingRoom == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        return existingRoom;

    }
    
    public List<Desk> findAllDesk(String roomId) {
        List<Desk> deskList = null;
        return deskList;
    }

    @Transactional
    public Room create(Room room) {
        Room existingRoom = roomRepository.create(room);
        return existingRoom; 
    }

    @Transactional
    public Room update(Room room) {
        Room existingRoom = roomRepository.findById(room.getId());
        if (existingRoom == null) {
            existingRoom = roomRepository.create(room);
            return existingRoom;
        }
        System.out.println("In room service update " + room.getId() + " " + existingRoom.toString());
        return roomRepository.update(room);
    }

    @Transactional
    public void delete(String id) {
        roomRepository.delete(id);
    }

}
