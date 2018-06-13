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
        String query = "SELECT * from LaborFundContribution where appUserId=" + userID;
        return entityManager.createQuery(query, LaborFundContribution.class).getSingleResult();
    }
}
