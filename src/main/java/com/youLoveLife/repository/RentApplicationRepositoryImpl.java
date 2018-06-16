package com.youLoveLife.repository;


import com.youLoveLife.domain.applications.RentApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class RentApplicationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void sendApplication(RentApplication rentApplication) {
        entityManager.persist(rentApplication);
    }

    @Transactional
    public List<RentApplication> getAllRentApplication() {
        return entityManager.createQuery("select r from RentApplication r", RentApplication.class).getResultList();
    }

    @Transactional
    public RentApplication confirmApplication(Integer applicationID) {
        List<RentApplication> list = getAllRentApplication();
        Iterator<RentApplication> iterator = list.iterator();
        RentApplication tmp = null;

        while (iterator.hasNext()) {
            tmp = iterator.next();

            if(tmp.getApplicationID().equals(Long.valueOf(applicationID))) {
                tmp.setApproved(true);
                entityManager.merge(tmp);
                break;
            }
        }

        return tmp;
    }

}
