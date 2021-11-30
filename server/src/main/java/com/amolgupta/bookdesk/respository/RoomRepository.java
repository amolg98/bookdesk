package com.amolgupta.bookdesk.respository;

import java.sql.Timestamp;
import java.util.List;
import com.amolgupta.bookdesk.entity.Room;
import com.amolgupta.bookdesk.entity.Desk;

public interface RoomRepository {
    
    List<Room> findAll(); 
    Room findById(String id);
    List<Room> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp);
    List<Desk> findAllDesk(String roomId);

    // Only Admin functions
    Room create(Room room);
    Room update(Room room);
    void delete(String id);

}
