package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.SocialContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SocialContributionRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;

    public SocialContribution getSocialContribution(Integer userID) {
        appUserRepositoryImpl.updateUser(userID);
        //String query = "SELECT * from SocialContribution where appUserId=" + userID;
        //return entityManager.createQuery(query, SocialContribution.class).getSingleResult();
        return entityManager.createQuery("SELECT s FROM SocialContribution s WHERE s.appUser.id LIKE :userID", SocialContribution.class)
                .setParameter("userID", Long.valueOf(userID))
                .getSingleResult();
    }
}
