package org.laurieandthegang.parkshark.repository;

import org.laurieandthegang.parkshark.domain.parkinglot.ParkingLot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ParkingLotRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addParkingLot(ParkingLot parkingLot) {
        entityManager.persist(parkingLot);
    }
}
