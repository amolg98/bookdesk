package com.amolgupta.bookdesk.respository;

import java.util.List;
import java.sql.Timestamp;

import com.amolgupta.bookdesk.entity.Desk;

public interface DeskRepository {
    
    List<Desk> findAll();
    List<Desk> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp);
    Desk findById(String id);
    Desk create(Desk desk);
    Desk update(Desk desk);
    void delete(String id);

}
