package com.youLoveLife.repository;

import com.youLoveLife.domain.Company;
import com.youLoveLife.domain.Contribution.Job;
import com.youLoveLife.domain.user.Address;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.domain.user.CreatingUserTools;
import oracle.net.aso.q;
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
    private CompanyRepositoryImpl companyRepository;

    public List<AppUser> getUsersList() {
        String query = "from AppUser user";
        return em.createQuery(query, AppUser.class).getResultList();
    }

    public List<Long> getUsersId() {
        String query = "select u.id from AppUser u";
        return em.createQuery(query, Long.class).getResultList();
    }

    public int contUsers() {
        String query = "select count(*) from AppUser";
        return (int) em.createQuery(query).getSingleResult();
    }

    public AppUser getUserByID(Integer id) {
        return em.createQuery("SELECT u FROM AppUser u WHERE u.id LIKE :id", AppUser.class)
                .setParameter("id", Long.valueOf(id))
                .getSingleResult();
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

        //********************** USER 1 *********************************//
        Date dateOfBirth = CreatingUserTools.setDate(1970,3,24);

        Address companyAddress = new Address("Pińczów", "10", "3 Maja", "28-400 Pińczów", "Polska");

        AppUser company1Owner = new AppUser("Janusz", "Biznesu", companyAddress, dateOfBirth, "janusz", "janusz", role2);

        Company company1 = new Company(company1Owner, "Spółdzielnia mieszkaniowa", companyAddress);
        company1Owner.setCurrentCompany(company1);
        company1Owner.setOwnCompany(company1);

        List<Job> jobList3 = new ArrayList<>();

        Date jobFrom = CreatingUserTools.setDate(1990,1,11);
        Date jobTo = CreatingUserTools.setDate(2030,6,6);

        Job job = new Job("Szef firmy w " + company1.getCompanyName(), jobFrom, jobTo, Double.valueOf(16000));
        job.setAppUser(company1Owner);
        jobList3.add(job);

        company1Owner.setJobsList(jobList3);


        //***************************** USER 2 ***********************************//
        Address address = new Address("Pińczów", "12", "Grodziskowa", "28-400 Pińczów", "Polska");
        dateOfBirth = CreatingUserTools.setDate(1960,6,11);

        AppUser user1 = new AppUser("Tadeusz", "Kozieł", address, dateOfBirth, "tadek", "tadek", role1);
        user1.setCurrentCompany(company1);

        List<Job> list1 = new ArrayList<>();

        jobFrom = CreatingUserTools.setDate(1980,4,21);
        jobTo = CreatingUserTools.setDate(1990,0,8);
        job = new Job("Hydraulik", jobFrom, jobTo, Double.valueOf(1500));
        job.setAppUser(user1);
        list1.add(job);

        jobFrom = CreatingUserTools.setDate(1990,1,14);
        jobTo = CreatingUserTools.setDate(2004,1,10);
        job = new Job("Kierowca ciężarówki", jobFrom, jobTo, Double.valueOf(3000));
        job.setAppUser(user1);
        list1.add(job);

        jobFrom = CreatingUserTools.setDate(2004,3,18);
        jobTo = CreatingUserTools.setDate(2008,6,10);
        job = new Job("Hydraulik w Spółdzielni mieszkaniowej", jobFrom, jobTo, Double.valueOf(2500));
        job.setAppUser(user1);
        list1.add(job);

        jobFrom = CreatingUserTools.setDate(2008,6,11);
        jobTo = CreatingUserTools.setDate(2023,7,25);
        job = new Job("Zastępca Prezesa Zarządu w Spółdzielni mieszkaniowej", jobFrom, jobTo, Double.valueOf(7000));
        job.setAppUser(user1);
        list1.add(job);

        user1.setJobsList(list1);


        //******************* USER 3 ***************************************************
        address = new Address("Kielce", "23", "Źródłowa", "25-335 Kielce", "Polska");
        dateOfBirth = CreatingUserTools.setDate(1958,4,22);

        AppUser user2 = new AppUser("Józef", "Kozieł", address, dateOfBirth, "jozek", "jozek", role2);

        Company company2 = new Company(user2, "Firma budowlana", address);

        List<Job> list2 = new ArrayList<>();

        jobFrom = CreatingUserTools.setDate(1980,3,11);
        jobTo = CreatingUserTools.setDate(2030,6,6);

        job = new Job("Szef firmy w " + company2.getCompanyName(), jobFrom, jobTo, Double.valueOf(23000));
        job.setAppUser(user2);
        list2.add(job);

        user2.setJobsList(list2);
        user2.setOwnCompany(company2);
        user2.setCurrentCompany(company2);


        user1.updateData();
        user2.updateData();
        company1Owner.updateData();
        company1Owner.updateData();


        if(!isExistUser(user1, users)){
            em.persist(user1);
        }
        if(!isExistUser(user2, users)) {
            em.persist(user2);
        }
        if(!isExistUser(company1Owner, users)) {
            em.persist(company1Owner);
        }

    }

    @Transactional
    public void updateContributions() {
        List<AppUser> list = getUsersList();
        Iterator<AppUser> iterator = list.iterator();

        List<Company> companies = companyRepository.getCompanyList();
        Iterator<Company> companyIterator = companies.iterator();

        while (iterator.hasNext()) {
            AppUser user = iterator.next();
            user.updateContribution();
            user.updateData();
            if(user.getId()<=100) {
                Company company = companies.get(user.getId().intValue()-1);
                System.out.println("\n\n\n\n\n\n\n\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n\n" + company + "*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n\n\n\n\n\n\n\n\n\n\n\n");
                user.setOwnCompany(company);
            }
        }
    }

    @Transactional
    public AppUser updateUser(Integer userID) {
        List<Long> usersID = this.getUsersId();
        Iterator<Long> iterator = usersID.iterator();
        Long tmp = null;
        AppUser user = null;

        while (iterator.hasNext()) {
             tmp = iterator.next();

            if(tmp.equals(Long.valueOf(userID))) {
                user = getUserByID(tmp.intValue());
                user.updateData();
                em.merge(user);
                break;
            }
        }

        return user;
    }

    public AppUser setUserOwnCopany(Company company) {
        AppUser appUser = em.createQuery("select u from AppUser u where u.id like :id", AppUser.class)
                .setParameter("id", company.getOwner().getId())
                .getSingleResult();

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 50);

        Job job = new Job("Szef firmy w " + company.getCompanyName(), new Date(), c.getTime(), Double.valueOf(3000));
        job.setAppUser(appUser);
        appUser.addNewJob(job);
        appUser.setCurrentCompany(company);
        appUser.setOwnCompany(company);

        company.addNewEmployee(appUser);
        em.merge(company);
        return em.merge(appUser);
    }

    public AppUser addNewJob(AppUser employee, Company company, Job job) {
        AppUser appUser = em.createQuery("select u from AppUser u where u.id like :id", AppUser.class)
                .setParameter("id", employee.getId())
                .getSingleResult();

        appUser.addNewJob(job);
        appUser.setCurrentCompany(company);

        return em.merge(appUser);
    }

    public void terminateContract(Long userID) {
        AppUser appUser = em.createQuery("select u from AppUser u where u.id like :id", AppUser.class)
                .setParameter("id", userID)
                .getSingleResult();

        appUser.getLastJob().setToDate(new Date());
        appUser.setCurrentCompany(null);
        em.merge(appUser);
    }

    public List<AppUser> getUsersBySurname(String surname) {
        return em.createQuery("select u from AppUser u where ( UPPER(u.surname) like :surname or UPPER(u.name) like :surname )", AppUser.class)
                .setParameter("surname", "%" + surname.toUpperCase() + "%")
                .getResultList();
    }

}
