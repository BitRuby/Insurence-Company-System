package com.youLoveLife.web;

import com.youLoveLife.domain.Message;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.repository.MessageRepository;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/messageToAll", method = RequestMethod.POST)
    public void sendMessageToEveryone(@RequestParam String topic, @RequestParam String message) {
        System.out.println("\n\n\n\n******************************************\n" + topic + "\n" + message + "***************************");
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
    public ResponseEntity receiveMessages(@PathVariable Integer userID) {
        List<Message> list = messageRepositoryImpl.receiveMessages(userID);

        if(list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/setAsReaded/{messageID}", method = RequestMethod.GET)
    public ResponseEntity setMessageAsReaded(@PathVariable Integer messageID) {
        messageRepositoryImpl.readMessage(messageID);
        Message message = messageRepository.getOne(Long.valueOf(messageID));

        if(message != null)
            return new ResponseEntity(message, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        Message message = messageRepository.findOne(id);
        messageRepository.delete(message);
    }
}
