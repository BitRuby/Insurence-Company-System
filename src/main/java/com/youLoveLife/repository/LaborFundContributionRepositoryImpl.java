package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.LaborFundContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LaborFundContributionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;

    public LaborFundContribution getLaborFundContribution(Integer userID) {
        appUserRepositoryImpl.updateUser(userID);
        return entityManager.createQuery("SELECT l FROM LaborFundContribution l WHERE l.appUser.id LIKE :userID", LaborFundContribution.class)
                .setParameter("userID", Long.valueOf(userID))
                .getSingleResult();
    }
}
