package com.youLoveLife.web;

import com.youLoveLife.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageRestController {

    @Autowired
    MessageRepository messageRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/messageToAll/", method = RequestMethod.POST)
    public void sendMessageToEveryone(@RequestBody String topic, @RequestBody String message) {
        messageRepository.sendEveryone(topic, message);
    }

    @RequestMapping(value = "/messageToUser/", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String topic, @RequestBody String message, @RequestBody Integer userId){
        messageRepository.sendMessage(topic, message, userId);
    }
}
