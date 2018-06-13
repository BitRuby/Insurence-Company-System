package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.Job;
import com.youLoveLife.domain.user.Address;
import com.youLoveLife.domain.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class AppUserRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Lazy
    private AppUserRepository appUserRepository;


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

        role1.add("ADMIN");
        role1.add("USER");
        role2.add("USER");



        Address address1 = new Address("Pińczów", "12", "Grodziskowa", "28-400 Pińczów", "Polska");
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(1960, 6, 11);
        Date date1 = cal.getTime();


        AppUser user1 = new AppUser("Tadeusz", "Kozieł", address1, date1, "tadek", "tadek", role1);

        List<Job> list1 = new ArrayList<>();

        cal.set(1980,4,21);
        Date jobDF1 = cal.getTime();
        cal.set(1990,0,8);
        Date jobDT1 = cal.getTime();

        cal.set(1990,1,14);
        Date jobDF2 = cal.getTime();
        cal.set(2004,1,10);
        Date jobDT2 = cal.getTime();

        cal.set(2004,3,18);
        Date jobDF3 = cal.getTime();
        cal.set(2008, 6, 10);
        Date jobDT3 = cal.getTime();

        cal.set(2008, 6,11);
        Date jobDF4 = cal.getTime();
        cal.set(2023, 7,25);
        Date jobDT4 = cal.getTime();

        Job job1 = new Job("Hydraulik", jobDF1, jobDT1, Double.valueOf(1500), user1);
        Job job2 = new Job("Kierowca ciężarówki", jobDF2, jobDT2, Double.valueOf(3000), user1);
        Job job3 = new Job("Hydraulik w Spółdzielni mieszkaniowej", jobDF3, jobDT3, Double.valueOf(2500), user1);
        Job job4 = new Job("Zastępca Prezesa Zarządu w Spółdzielni mieszkaniowej", jobDF4, jobDT4, Double.valueOf(7000), user1);

        list1.add(job1);
        list1.add(job2);
        list1.add(job3);
        list1.add(job4);

        user1.setJobsList(list1);




        Address address2 = new Address("Kielce", "23", "Źródłowa", "25-335 Kielce", "Polska");
        cal.set(1958, 4, 22);
        Date date2 = cal.getTime();


        AppUser user2 = new AppUser("Józef", "Kozieł", address2, date2, "jozek", "jozek", role2);

        List<Job> list2 = new ArrayList<>();

        cal.set(1980, 3,11);
        Date job2DF = cal.getTime();
        cal.set(2030,6,6);
        Date job2DT = cal.getTime();

        Job job2J = new Job("Szef firmy", job2DF, job2DT, Double.valueOf(6000), user2);

        user2.setJobsList(list2);

        list2.add(job2J);

        user1.updateData();
        user2.updateData();


        if(!isExistUser(user1, users)){
            em.persist(user1);
        }
        if(!isExistUser(user2, users)) {
            em.persist(user2);
        }

    }

    public AppUser updateUser(Integer userID) {
        List<AppUser> users = this.getUsersList();
        Iterator<AppUser> iterator = users.iterator();
        AppUser tmp = null;

        while (iterator.hasNext()) {
             tmp = iterator.next();

            if(tmp.getId().equals(userID)) {
                tmp.updateData();
                /*
                    TODO jesli nie bedzie dzialac uzyc:

                    public void updateArtist(Artist artist) {
                        manager.getTransaction().begin();
                        manager.merge(artist);
                        manager.getTransaction().commit();
                    }
                 */
                appUserRepository.save(tmp);
                break;
            }
        }

        return tmp;
    }

}
