package com.amolgupta.bookdesk.respository;

import com.amolgupta.bookdesk.entity.Booking;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;


@Repository
public class BookingRepositoryImpl implements BookingRepository {
    
    @PersistenceContext
    private EntityManager bookingEntityManager;

    public List<Booking> findAll() {
        TypedQuery<Booking> query = bookingEntityManager.createNamedQuery("Booking.findAll", Booking.class);
        return query.getResultList();
    }

    public Booking findById(String id) {
        return bookingEntityManager.find(Booking.class, id);
    }
    
    public List<Booking> findByRoom(String roomId) {
        TypedQuery<Booking> query = bookingEntityManager.createNamedQuery("Booking.findByRoom", Booking.class);
        return query.getResultList();
    }

    public Booking create(Booking booking) {
        bookingEntityManager.persist(booking);
        return booking;
    }

    public Booking update(Booking booking) {
        bookingEntityManager.merge(booking);
        return booking;
    }

    public void delete(String id) {
        bookingEntityManager.remove(id);
    }

}
