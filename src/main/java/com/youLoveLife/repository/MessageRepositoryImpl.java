package com.youLoveLife.repository;

import com.youLoveLife.domain.Company;
import com.youLoveLife.domain.Contribution.Job;
import com.youLoveLife.domain.Contribution.Rent;
import com.youLoveLife.domain.Contribution.SocialContribution;
import com.youLoveLife.domain.applications.Application;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.domain.Message;
import com.youLoveLife.enums.ApplicationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Repository
public class MessageRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AppUserRepositoryImpl appUserRepository;
    @Autowired
    private SocialContributionRepositoryImpl socialContributionRepository;
    @Autowired
    private CompanyRepositoryImpl companyRepository;

    @Transactional
    public void sendEveryone(String topic, String message) {
        List<AppUser> list = appUserRepository.getUsersList();
        Iterator<AppUser> iterator = list.iterator();

        while (iterator.hasNext()) {
            AppUser temp = iterator.next();
            Message msg = new Message(temp.getId(), topic, message);
            em.persist(msg);
        }
    }

    @Transactional
    public void sendMessage(String topic, String message, Integer userID) {
        List<AppUser> list = appUserRepository.getUsersList();
        Iterator<AppUser> iterator = list.iterator();

        while (iterator.hasNext()) {
            AppUser temp = iterator.next();
            if(temp.getId().equals(Long.valueOf(userID))) {
                Message msg = new Message(temp.getId(), topic, message);
                em.persist(msg);
            }
        }
    }

    public List<Message> receiveMessages(Integer userID) {
        return em.createQuery("SELECT m FROM Message m WHERE m.userID LIKE :userID", Message.class)
                .setParameter("userID", Long.valueOf(userID))
                .getResultList();
    }

    public void readMessage(Integer messageID) {
        Message message = em.createQuery("SELECT m FROM Message m WHERE m.id LIKE :id", Message.class)
                .setParameter("id", Long.valueOf(messageID))
                .getSingleResult();
        message.setReaded(true);
        em.merge(message);
    }

    public void sendConfirmation(Application application) {
        if(application.getType() == ApplicationType.RENT)
            getRentMessage(application);
        if(application.getType() == ApplicationType.NEW_COMPANY)
            getNewCompanyMessage(application);
        if(application.getType() == ApplicationType.UNREGISTER_COMPANY)
            getUnregisteredCompanyMessage(application);
    }

    private void getRentMessage(Application application) {
        SocialContribution socialContribution = socialContributionRepository.getSocialContribution(application.getUserID().intValue());
        Rent rent = socialContribution.getRent();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String from = sdf.format(rent.getFromDate());
        String to =  sdf.format(rent.getToDate());

        String topic = "Zatwierdzenie twojego wniosku o rentę";
        String message = "Witaj " + application.getName() + " " + application.getSurname() + ". " + "Twój wniosek o numerze " +
                application.getApplicationID() + " został rozpatrzony i zatwierdzony przez administratora. Od dnia " + from +
                " do " + to + " będzie wypłacana renta w wysokości " + rent.getAmount() + " zł." +
                "\n\nSzczegółowe informacje:" +
                "\nNumer wniosku o rentę: " + application.getApplicationID() +
                "\nImię: " + application.getName() +
                "\nNazwisko: " + application.getSurname() +
                "\nMiasto: " + application.getCity() +
                "\nBudynek: " + application.getBuilding() +
                "\nUlica: " + application.getStreet() +
                "\nKod pocztowy: " + application.getPostcode() +
                "\nKraj: " + application.getCountry() +
                "\nData rozpoczęcia wypłacania renty: " + from +
                "\nData zakończenia wypłacania renty: " + to +
                "\nWypłacana kwota: " + rent.getAmount();

        sendMessage(topic, message, application.getUserID().intValue());
    }

    private void getNewCompanyMessage(Application application) {
        Company company = companyRepository.getCompanyByUserID(application.getUserID().intValue());

        String topic = "Zatwierdzenie twojego wniosku o zgłoszenie firmy";
        String message = "Witaj " + application.getName() + " " + application.getSurname() + ". " + "Twój wniosek o numerze " +
                application.getApplicationID() + " został rozpatrzony i zatwierdzony przez administratora." +
                " Twoja firma została zgłoszona do ubezpieczenia. Od dzisiaj możesz zgłaszać pracowników do ubezpieczenia." +
                "\n\nSzczegółowe informacje:" +
                "\nNumer wniosku: " + application.getApplicationID() +
                "\nImię: " + application.getName() +
                "\nNazwisko: " + application.getSurname() +
                "\nMiasto: " + application.getCity() +
                "\nBudynek: " + application.getBuilding() +
                "\nUlica: " + application.getStreet() +
                "\nKod pocztowy: " + application.getPostcode() +
                "\nKraj: " + application.getCountry() +
                "\nNazwa firmy: " + company.getCompanyName() +
                "\nNIP: " + company.getNip() +
                "\nREGON: " + company.getRegon();

        sendMessage(topic, message, application.getUserID().intValue());
    }

    private void getUnregisteredCompanyMessage(Application application) {
        Company company = companyRepository.getCompanyByUserID(application.getUserID().intValue());

        String topic = "Zatwierdzenie twojego wniosku o wyrejestrowanie firmy";
        String message = "Witaj " + application.getName() + " " + application.getSurname() + ". " + "Twój wniosek o numerze " +
                application.getApplicationID() + " został rozpatrzony i zatwierdzony przez administratora." +
                " Twoja firma została wyrejestrowana. Od dzisiaj nie możesz zgłaszać pracowników do ubezpieczenia." +
                "\n\nSzczegółowe informacje:" +
                "\nNumer wniosku: " + application.getApplicationID() +
                "\nImię: " + application.getName() +
                "\nNazwisko: " + application.getSurname() +
                "\nMiasto: " + application.getCity() +
                "\nBudynek: " + application.getBuilding() +
                "\nUlica: " + application.getStreet() +
                "\nKod pocztowy: " + application.getPostcode() +
                "\nKraj: " + application.getCountry() +
                "\nNazwa firmy: " + company.getCompanyName() +
                "\nNIP: " + company.getNip() +
                "\nREGON: " + company.getRegon();

        sendMessage(topic, message, application.getUserID().intValue());
    }

    public void sendTerminateContractMessage(Integer userID, Integer companyID) {
        List<AppUser> list = appUserRepository.getUsersList();
        Iterator<AppUser> iterator = list.iterator();
        AppUser appUser = null;

        while (iterator.hasNext()) {
            appUser = iterator.next();

            if(appUser.getId().equals(Long.valueOf(userID)))
                break;
        }

        Company company = companyRepository.getCompanyByID(companyID);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String from = sdf.format(appUser.getLastJob().getFromDate());
        String to =  sdf.format(appUser.getLastJob().getToDate());


        String topic = "Twoja umowa o pracę została wypowiedzona";
        String message = "Witaj " + appUser.getName() + " " + appUser.getSurname() + ". " + "Twoja umowa o pracę z firmą  " +
                company.getCompanyName() + " została rozwiązana." +
                " Zostałeś wypisany z ubezpieczenia zdroweotnego i ubezpieczenia pracy." +
                "\n\nInformacje o firmie:" +
                "\nNazwa fimry: " + company.getCompanyName() +
                "\nREGON: " + company.getRegon() +
                "\nNIP: " + company.getRegon() +
                "\nMiasto: " + company.getAddress().getCity() +
                "\nBudynek: " + company.getAddress().getBuilding() +
                "\nUlica: " + company.getAddress().getStreet() +
                "\nKod pocztowy: " + company.getAddress().getPostcode() +
                "\nKraj: " + company.getAddress().getCountry() +
                "\nZajmowane stanowisko: " + appUser.getLastJob().getCompanyInfo() +
                "\nRozpoczęcie pracy: " + from +
                "\nZakończenie pracy: " + to;

        sendMessage(topic, message, userID);
    }

    public void sendNewJobInfo(AppUser appUser, Integer companyID, Job job) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String from = sdf.format(job.getFromDate());
        String to =  sdf.format(job.getToDate());

        Company company = companyRepository.getCompanyByID(companyID);

        String topic = "Zostałeś zgłoszony do ubezpiecznia przez pracodawcę";
        String message = "Witaj " + appUser.getName() + " " + appUser.getSurname() + ". " + "Twoja umowa o pracę z firmą  " +
                company.getCompanyName() + " została podpisana i zostałeś zgłoszony do ubezpieczenia przez swojego pracodawcę." +
                " Przysługuje Ci ubezpieczenie zdroweotnego, socjalne i ubezpieczenie pracy." +
                "\n\nInformacje o firmie:" +
                "\nNazwa fimry: " + company.getCompanyName() +
                "\nREGON: " + company.getRegon() +
                "\nNIP: " + company.getRegon() +
                "\nMiasto: " + company.getAddress().getCity() +
                "\nBudynek: " + company.getAddress().getBuilding() +
                "\nUlica: " + company.getAddress().getStreet() +
                "\nKod pocztowy: " + company.getAddress().getPostcode() +
                "\nKraj: " + company.getAddress().getCountry() +
                "\nZajmowane stanowisko: " + appUser.getLastJob().getCompanyInfo() +
                "\nRozpoczęcie pracy: " + from +
                "\nZakończenie pracy: " + to;

        sendMessage(topic, message, appUser.getId().intValue());
    }


}
