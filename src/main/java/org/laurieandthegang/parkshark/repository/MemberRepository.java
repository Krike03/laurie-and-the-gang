package org.laurieandthegang.parkshark.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.laurieandthegang.parkshark.domain.people.*;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addMember(Member member){
        entityManager.persist(member);
    }

    public List<Member> getAllMembers() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
