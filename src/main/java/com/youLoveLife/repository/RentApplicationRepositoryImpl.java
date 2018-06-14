package com.youLoveLife.repository;


import com.youLoveLife.domain.applications.RentApplication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RentApplicationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void sendApplication(RentApplication rentApplication) {
        entityManager.persist(rentApplication);
    }

    public List<RentApplication> getAllRentApplication() {
        return entityManager.createQuery("select r from RentApplication r", RentApplication.class).getResultList();
    }

}
