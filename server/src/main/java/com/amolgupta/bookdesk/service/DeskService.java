package com.amolgupta.bookdesk.service;

import java.util.List;
import java.sql.Timestamp;

import com.amolgupta.bookdesk.entity.Desk;

public interface DeskService {
    
    List<Desk> findAll();
    Desk findById(String id);
    List<Desk> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp);

    Desk create(Desk desk);
    Desk update(Desk desk);
    void delete(String id);

}
