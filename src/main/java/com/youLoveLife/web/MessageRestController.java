package com.youLoveLife.web;

import com.youLoveLife.domain.Message;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.repository.MessageRepository;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
public class MessageRestController {

    @Autowired
    private MessageRepositoryImpl messageRepositoryImpl;
    @Autowired
    private MessageRepository messageRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/messageToAll/", method = RequestMethod.POST)
    public void sendMessageToEveryone(@RequestBody String topic, @RequestBody String message) {
        messageRepositoryImpl.sendEveryone(topic, message);
    }

    @RequestMapping(value = "/messageToUser/", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String topic, @RequestBody String message, @RequestBody List<AppUser> users){
        Iterator<AppUser> iterator = users.iterator();

        while (iterator.hasNext()) {
            messageRepositoryImpl.sendMessage(topic, message, iterator.next().getId().intValue());
        }


    }

    @RequestMapping(value = "/receiveMessages/{userID}", method = RequestMethod.GET)
    public List<Message> receiveMessages(@PathVariable Integer userID) {
        return messageRepositoryImpl.receiveMessages(userID);
    }

    @RequestMapping(value = "/setAsReaded/{messageID}", method = RequestMethod.GET)
    public void setMessageAsReaded(@PathVariable Integer messageId) {
        messageRepositoryImpl.readMessage(messageId);
    }

    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        Message message = messageRepository.findOne(id);
        messageRepository.delete(message);
    }
}
