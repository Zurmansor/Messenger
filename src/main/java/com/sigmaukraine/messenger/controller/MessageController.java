package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Message;
import com.sigmaukraine.messenger.repository.MessageRepository;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.validation.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private MessageValidator messageValidator;

    public MessageController() {
    }

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository, MessageValidator messageValidator) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageValidator = messageValidator;
    }

    @RequestMapping(value = "subjects/{subjectId}/chats/{chatId}/messages", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String list(Model model, @PathVariable Integer subjectId, @PathVariable Integer chatId) {
        List<Message> messages = this.messageRepository.getListMessagesByChatId(chatId);
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        model.addAttribute("login", userDetails.getUsername());
        return "messages/list";
    }


    @RequestMapping(value = "subjects/{subjectId}/chats/{chatId}/messages/add", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public String addMessage(@ModelAttribute("message") Message message, BindingResult bindingResult, @PathVariable Integer subjectId, @PathVariable Integer chatId) {
        this.messageValidator.validate(message, bindingResult);

        if (bindingResult.hasErrors()) {
            return "error";
        }

        message.setChatId(chatId);

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sigmaukraine.messenger.domain.User user = userRepository.getUserByLogin(userDetails.getUsername());
        message.setUserId(user.getId());
        this.messageRepository.addMessage(message);

        return "ok";
//        return "redirect:/subjects/"+subjectId +"/chats/"+ chatId +"/messages";
    }

/*    @RequestMapping(value = "/messages/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String removeMessage(@PathVariable Integer id){
        this.messageRepository.removeMessage(id);
        return "redirect:/messages";
    }*/
}
