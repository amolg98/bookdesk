package com.amolgupta.bookdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import com.amolgupta.bookdesk.entity.Desk;
import com.amolgupta.bookdesk.respository.DeskRepository;

@Service
public class DeskServiceImpl implements DeskService {
    
    @Autowired
    private DeskRepository deskRepository;
    
    public List<Desk> findAll() {
        return deskRepository.findAll();
    }

    public List<Desk> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp) {
        List<Desk> allDesks = new ArrayList<>();
        allDesks = deskRepository.findAllByDate(startTimestamp, stopTimestamp);
        return allDesks;
    }
    
    public Desk findById(String id) {
        Desk desk = deskRepository.findById(id);
        // Optional<User> user = userRepository.findById(id);

        if (desk == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return desk;
    }
    
    @Transactional
    public Desk create(Desk desk) {
        Desk existingDesk = deskRepository.findById(desk.getId());
        if (existingDesk != null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Desk currDesk = deskRepository.create(desk);
        return currDesk;
    }
    
    @Transactional
    public Desk update(Desk desk) {
        Desk existingDesk = deskRepository.findById(desk.getId());
        if (existingDesk == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Desk currDesk = deskRepository.update(desk);
        return currDesk;
    }
    
    @Transactional
    public void delete(String id) {
        Desk existingDesk = deskRepository.findById(id);
        if (existingDesk == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        deskRepository.delete(id);
    }

}
