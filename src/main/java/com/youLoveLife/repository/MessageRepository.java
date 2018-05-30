package com.youLoveLife.repository;

import com.youLoveLife.domain.AppUser;
import com.youLoveLife.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class MessageRepository {

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

    public String[] receiveMessage(Integer userID) {

        return null;
    }
}
