package org.laurieandthegang.parkshark.repository;

import org.laurieandthegang.parkshark.domain.division.Division;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DivisionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertDivision(Division division){
        entityManager.persist(division);
    }

    public List<Division> getAllDivisions() {
        return entityManager.createQuery("select d from Division d", Division.class).getResultList();
    }

    public Division getDivisionById(int id) {
        return entityManager.find(Division.class, id);
    }
}
