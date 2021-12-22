package org.laurieandthegang.parkshark.repository;

import org.laurieandthegang.parkshark.domain.division.Division;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class DivisionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertDivision(Division division){
        entityManager.persist(division);
    }
}
