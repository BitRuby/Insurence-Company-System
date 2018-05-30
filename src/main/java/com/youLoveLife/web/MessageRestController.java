package com.youLoveLife.web;

import com.youLoveLife.domain.Message;
import com.youLoveLife.repository.MessageRepository;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void sendMessage(@RequestBody String topic, @RequestBody String message, @RequestBody Integer userId){
        messageRepositoryImpl.sendMessage(topic, message, userId);
    }

    @RequestMapping(value = "/receiveMessage/{userID}", method = RequestMethod.GET)
    public Message receiveMessage(@PathVariable Integer userID) {
        return messageRepositoryImpl.receiveMessage(userID);
    }

    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        Message message = messageRepository.findOne(id);
        messageRepository.delete(message);
    }
}
