package com.youLoveLife.repository;


import com.youLoveLife.domain.applications.Application;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class ApplicationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void sendApplication(Application application) {
        entityManager.persist(application);
    }

    @Transactional
    public List<Application> getAllApplication() {
        return entityManager.createQuery("select a from Application a", Application.class).getResultList();
    }

    @Transactional
    public Application confirmApplication(Integer applicationID) {
        List<Application> list = getAllApplication();
        Iterator<Application> iterator = list.iterator();
        Application tmp = null;

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
