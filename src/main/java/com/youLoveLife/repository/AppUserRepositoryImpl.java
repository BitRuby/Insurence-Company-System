package com.youLoveLife.repository;

import com.youLoveLife.domain.Address;
import com.youLoveLife.domain.AppUser;
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


    public List<AppUser> getUsersList() {
        String query = "from AppUser user";
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

        Address address1 = new Address("Pińczów", "12", "Grodziskowa", "28-400 Pińczów", "Polska");
        Address address2 = new Address("Kielce", "23", "Źródłowa", "25-335 Kielce", "Polska");

        role1.add("ADMIN");
        role1.add("USER");
        role2.add("USER");

        AppUser user1 = new AppUser("Tadeusz", "Kozieł", address1, "tadek", "tadek", role1);
        AppUser user2 = new AppUser("Józef", "Kozieł", address2, "jozek", "jozek", role2);


        if(!isExistUser(user1, users)){
            System.out.println("BICZ 1");
            em.persist(user1);
        }
        if(!isExistUser(user2, users)) {
            System.out.println("BICZ 2");
            em.persist(user2);
        }

    }

}
