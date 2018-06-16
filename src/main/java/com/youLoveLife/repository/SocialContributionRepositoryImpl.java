package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.Rent;
import com.youLoveLife.domain.Contribution.SocialContribution;
import com.youLoveLife.domain.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class SocialContributionRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;

    @Transactional
    public SocialContribution getSocialContribution(Integer userID) {
        appUserRepositoryImpl.updateUser(userID);
        return entityManager.createQuery("SELECT s FROM SocialContribution s WHERE s.appUser.id LIKE :userID", SocialContribution.class)
                .setParameter("userID", Long.valueOf(userID))
                .getSingleResult();
    }

    public void setRent(Integer userID, Rent rent) {
        List<AppUser> list = appUserRepositoryImpl.getUsersList();
        Iterator<AppUser> iterator = list.iterator();

        while (iterator.hasNext()) {
            AppUser tmp = iterator.next();

            if (tmp.getId().equals(Long.valueOf(userID))) {
                tmp.getSocialContribution().getRent().setAmount(rent.getAmount());
                tmp.getSocialContribution().getRent().setFromDate(rent.getFromDate());
                tmp.getSocialContribution().getRent().setPaid(true);
                tmp.getSocialContribution().getRent().setToDate(rent.getToDate());
                entityManager.merge(tmp);
                break;
            }
        }
    }
}
