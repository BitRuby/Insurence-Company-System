package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.HealthContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class HealthContributionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;

    public HealthContribution getHealthContribution(Integer userID) {
        appUserRepositoryImpl.updateUser(userID);

        return entityManager.createQuery("SELECT h FROM HealthContribution h WHERE h.appUser.id LIKE :userID", HealthContribution.class)
                .setParameter("userID", Long.valueOf(userID))
                .getSingleResult();
    }

}
