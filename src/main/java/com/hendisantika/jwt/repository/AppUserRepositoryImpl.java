package com.hendisantika.jwt.repository;

import com.hendisantika.jwt.domain.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class AppUserRepositoryImpl {

    @PersistenceContext
    EntityManager em;


    private List<AppUser> getUsersList() {
        String query = "from AppUser ser";
        return em.createQuery(query, AppUser.class).getResultList();
    }

    private boolean isExistUser(AppUser user, List<AppUser> users) {
        Iterator<AppUser> it = users.iterator();

        while (it.hasNext()) {
            AppUser userFromDB = it.next();

            if(userFromDB.equals(user))
                return true;
        }

        return false;
    }

    @Transactional
    public void setFirstUsers() {
        List<AppUser> users = getUsersList();

        List<String> role1 = new ArrayList<>();
        List<String> role2 = new ArrayList<>();

        role1.add("ADMIN");
        role1.add("USER");
        role2.add("USER");

        AppUser user1 = new AppUser("Tadeusz Kozieł", "tadek", "tadek", role1);
        AppUser user2 = new AppUser("Józef Kozieł", "jozek", "jozek", role2);


        if(!isExistUser(user1, users))
            em.persist(user1);
        if(!isExistUser(user2, users))
            em.persist(user2);

    }

}
