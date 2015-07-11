package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Message;
import com.sigmaukraine.messenger.repository.MessageRepository;
import com.sigmaukraine.messenger.validation.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private MessageValidator messageValidator;

    public MessageController() {
    }

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageValidator messageValidator) {
        this.messageRepository = messageRepository;
        this.messageValidator = messageValidator;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String list(Model model) {
        List<Message> messages = this.messageRepository.listAll();
        model.addAttribute("messages", messages);

        model.addAttribute("message", new Message());
        return "messages/list";
    }

/*
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String addMessage(Model model) {
        model.addAttribute("message", new Message());
//new
        return "messages/list";
    }
*/

    @RequestMapping(value = "/messages/add", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addMessage(@ModelAttribute("message") Message message, BindingResult bindingResult) {
        this.messageValidator.validate(message, bindingResult);

        if (bindingResult.hasErrors()) {
            //new /add
            return "messages/list";
        }

//        FIXME: created_by
        message.setChatId(1);
        message.setUserId(1);
        this.messageRepository.addMessage(message);
        return "redirect:/messages";
    }

/*    @RequestMapping(value = "/messages/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String removeMessage(@PathVariable Integer id){
        this.messageRepository.removeMessage(id);
        return "redirect:/messages";
    }*/
}
