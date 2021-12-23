//package org.laurieandthegang.parkshark.repository;
//
//import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//public class ContactPersonRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void addContactPerson(ParkingLot parkingLot) {
//        entityManager.persist(parkingLot.getContactPerson());
//    }
//}
