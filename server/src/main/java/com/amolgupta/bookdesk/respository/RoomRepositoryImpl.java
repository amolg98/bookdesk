package com.amolgupta.bookdesk.respository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.amolgupta.bookdesk.entity.Room;
import com.amolgupta.bookdesk.entity.Desk;

import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    
    @PersistenceContext
    private EntityManager roomEntityManager;

    public List<Room> findAll() {
        TypedQuery<Room> query = roomEntityManager.createNamedQuery("Room.findAll", Room.class);
        return query.getResultList();
    }

    public Room findById(String paramRoomId) {
        return roomEntityManager.find(Room.class, paramRoomId);
    }

    public List<Room> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp) {
        TypedQuery<Room> query = roomEntityManager.createNamedQuery("Room.findAllByDate", Room.class);
        query.setParameter("paramStartBookTime", startTimestamp);
        query.setParameter("paramStopBookTime", stopTimestamp);
        return query.getResultList();
    }

    public List<Desk> findAllDesk(String roomId) {
        // TypedQuery<Room> query = roomEntityManager.createNamedQuery("Room.findAllDesk", Room.class);
        List<Desk> list = null;
        return list;
    }

    public Room create(Room room) {
        roomEntityManager.persist(room);
        return room;
    }
    
    public Room update(Room room) {
        return roomEntityManager.merge(room);
    }
    
    public void delete(String id) {
        roomEntityManager.remove(id);
    }

}
