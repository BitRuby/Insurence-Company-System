package com.youLoveLife.repository;

import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class MessageRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private AppUserRepositoryImpl appUserRepository;

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
            if(userID.equals(temp.getId())) {
                Message msg = new Message(temp.getId(), topic, message);
                em.persist(msg);
            }
        }
    }

    public List<Message> receiveMessages(Integer userID) {
        //String query = "SELECT * from Message where userID=" + userID;
        //return em.createQuery(query, Message.class).getResultList();
        return em.createQuery("SELECT m FROM Message m WHERE m.userID LIKE :userID", Message.class)
                .setParameter("userID", Long.valueOf(userID))
                .getResultList();
    }

    public void readMessage(Integer messageID) {
        //String query = "SELECT * from Message where id=" + messageID;
        //Message message = em.createQuery(query, Message.class).getSingleResult();
        Message message = em.createQuery("SELECT m FROM Message m WHERE m.id LIKE :id", Message.class)
                .setParameter("id", Long.valueOf(messageID))
                .getSingleResult();
        message.setReaded(true);
        em.merge(message);
    }

    public void deleteMessage(Integer messageID) {

    }
}
