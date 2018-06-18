package com.youLoveLife.repository;

import com.youLoveLife.domain.Company;
import com.youLoveLife.domain.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class CompanyRepositoryImpl {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;
    @Autowired
    @Lazy
    private MessageRepositoryImpl messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void createCompany(Company company) {
        entityManager.persist(company);
    }

    public Company getCompanyByUserID(Integer userID) {
        List<Company> list = getCompanyList();
        Iterator<Company> iterator = list.iterator();

        while (iterator.hasNext()) {
            Company tmp = iterator.next();

            if (tmp.getOwner().getId().equals(Long.valueOf(userID)))
                return tmp;
        }
        return null;
    }

    public List<Company> getCompanyList() {
        return entityManager.createQuery("select c from Company c", Company.class).getResultList();
    }

    public Company getCompanyByID(Integer companyID) {
        List<Company> companyList = getCompanyList();
        Iterator<Company> iterator = companyList.iterator();

        while (iterator.hasNext()) {
            Company company = iterator.next();

            if(company.getCompanyID().equals(Long.valueOf(companyID)))
                return company;
        }
        return null;
    }

    public void addNewEmployee(AppUser employee, Integer companyID) {
        List<Company> list = getCompanyList();
        Iterator<Company> iterator = list.iterator();

        while (iterator.hasNext()) {
            Company tmp = iterator.next();

            if(tmp.getCompanyID().equals(Long.valueOf(companyID))) {
                tmp.addNewEmployee(employee);
            }

        }
    }

    public void deleteEmployee(Long userID, Long companyID) {
        List<Company> list = getCompanyList();
        Iterator<Company> iterator = list.iterator();

        AppUser user = appUserRepository.findOne(Long.valueOf(userID));

        while (iterator.hasNext()) {
            Company company = iterator.next();
            if(company.getCompanyID().equals(Long.valueOf(companyID))) {
                if(user != null) {
                    company.deleteEmployee(user);
                    entityManager.merge(company);
                }
            }
        }
    }

    public void unregisterCompany(Integer companyID) {
        Company company = getCompanyByID(companyID);

        List<AppUser> employees = company.getEmployees();

        Iterator<AppUser> iterator = employees.iterator();
        List<AppUser> employeesToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            AppUser appUser = iterator.next();
            employeesToRemove.add(appUser);
        }

        Iterator<AppUser> iteratorToRemove = employeesToRemove.iterator();

        while (iteratorToRemove.hasNext()) {
            Integer id = iteratorToRemove.next().getId().intValue();
            appUserRepositoryImpl.terminateContract(Long.valueOf(id));
            deleteEmployee(Long.valueOf(id), Long.valueOf(companyID));
            messageRepository.sendTerminateContractMessage(id, companyID);
        }
        AppUser owner = company.getOwner();
        owner.setCurrentCompany(null);
        owner.setOwnCompany(null);

        entityManager.merge(owner);
    }
}