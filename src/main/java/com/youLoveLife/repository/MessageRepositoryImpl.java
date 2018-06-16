package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.Rent;
import com.youLoveLife.domain.Contribution.SocialContribution;
import com.youLoveLife.domain.applications.RentApplication;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class MessageRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private AppUserRepositoryImpl appUserRepository;
    @Autowired
    private SocialContributionRepositoryImpl socialContributionRepository;

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

    public void sendConfirmation(RentApplication rentApplication) {
        SocialContribution socialContribution = socialContributionRepository.getSocialContribution(rentApplication.getUserID().intValue());
        Rent rent = socialContribution.getRent();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        String from = sdf.format(rent.getFromDate());
        String to =  sdf.format(rent.getToDate());

        String topic = "Zatwierdzenie twojego wniosku o rentę";
        String message = "Witaj " + rentApplication.getName() + " " + rentApplication.getSurname() + ". " + "Twój wniosek o numerze " +
                rentApplication.getApplicationID() + " został rozpatrzony i zatwierdzony przez administratora. Od dnia " + from +
                " do " + to + " będzie wypłacana renta w wysokości " + rent.getAmount() + " zł." +
                "\n\nSzczegółowe informacje:" +
                "\nNumer wniosku o rentę: " + rentApplication.getApplicationID() +
                "\nImię: " + rentApplication.getName() +
                "\nNazwisko: " + rentApplication.getSurname() +
                "\nMiasto: " + rentApplication.getCity() +
                "\nBudynek: " + rentApplication.getBuilding() +
                "\nUlica: " + rentApplication.getStreet() +
                "\nKod pocztowy: " + rentApplication.getPostcode() +
                "\nKraj: " + rentApplication.getCountry() +
                "\nData rozpoczęcia wypłacania renty: " + from +
                "\nData zakończenia wypłacania renty: " + to +
                "\nWypłacana kwota: " + rent.getAmount();

        sendMessage(topic, message, rentApplication.getUserID().intValue());
    }

}
