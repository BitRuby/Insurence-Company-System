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
    public ResponseEntity sendMessageToEveryone(@RequestParam String topic, @RequestParam String message) {
        try {
            messageRepositoryImpl.sendEveryone(topic, message);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/messageToUser", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestParam String topic, @RequestParam String message, @RequestParam List<AppUser> users){
        Iterator<AppUser> iterator = users.iterator();

        try {
            while (iterator.hasNext()) {
                messageRepositoryImpl.sendMessage(topic, message, iterator.next().getId().intValue());
            }
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            Message message = messageRepository.findOne(id);
            messageRepository.delete(message);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
