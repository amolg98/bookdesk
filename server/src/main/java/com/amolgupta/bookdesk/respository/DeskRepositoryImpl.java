package com.amolgupta.bookdesk.respository;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.amolgupta.bookdesk.entity.Desk;

import org.springframework.stereotype.Repository;

@Repository
public class DeskRepositoryImpl implements DeskRepository {
    
    @PersistenceContext
    private EntityManager deskEntityManager;

    public List<Desk> findAll() {
        List<Desk> allDesks = new ArrayList<>();
        try {
            TypedQuery<Desk> query = deskEntityManager.createNamedQuery("Desk.findAll", Desk.class);
            allDesks = query.getResultList();
            // System.out.println("All Desks" + allDesks);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            /**
             * Tryout the other query as well
             */
            query = deskEntityManager.createNamedQuery("Desk.findAllByDate", Desk.class);
            Date date = sdf.parse("2021-02-14T03:00");
            Timestamp paramStartBookTime = new Timestamp(date.getTime());

            date = sdf.parse("2021-02-14T06:00");
            Timestamp paramStopBookTime = new Timestamp(date.getTime());
            query.setParameter("paramStartBookTime", paramStartBookTime);
            query.setParameter("paramStopBookTime", paramStopBookTime);

            List<Desk> allDesks_temp = new ArrayList<>();
        
            allDesks_temp = query.getResultList();
            System.out.println(allDesks_temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("Later in allDesks");
        return allDesks;
    }


    public List<Desk> findAllByDate(Timestamp startTimestamp, Timestamp stopTimestamp) {
        TypedQuery<Desk> query = deskEntityManager.createNamedQuery("Desk.findAllByDate", Desk.class);
        query.setParameter("paramStartBookTime", startTimestamp);
        query.setParameter("paramStopBookTime", stopTimestamp);
        return query.getResultList();
    }

    public Desk findById(String id) {
        return deskEntityManager.find(Desk.class, id);
    }

    public Desk create(Desk desk) {
        deskEntityManager.persist(desk);
        return desk;
    }
    
    public Desk update(Desk desk) {
        return deskEntityManager.merge(desk);
    }
    
    public void delete(String id) {
        deskEntityManager.remove(id);
    }

}
