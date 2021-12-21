package org.laurieandthegang.parkshark.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.laurieandthegang.parkshark.domain.people.*;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addMember(Member member){
        entityManager.persist(member);
    }
}
