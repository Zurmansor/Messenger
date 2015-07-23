package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Message;
import com.sigmaukraine.messenger.repository.ChatRepository;
import com.sigmaukraine.messenger.repository.MessageRepository;
import com.sigmaukraine.messenger.repository.SubjectRepository;
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

import java.sql.Timestamp;
import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private MessageValidator messageValidator;
    private ChatRepository chatRepository;
    private SubjectRepository subjectRepository;

    public MessageController() {
    }

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository,
                             MessageValidator messageValidator, ChatRepository chatRepository,
                             SubjectRepository subjectRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.messageValidator = messageValidator;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "subjects/{subjectId}/chats/{chatId}/messages", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String list(Model model, @PathVariable Integer subjectId, @PathVariable Integer chatId) {
        List<Message> messages = this.messageRepository.getListMessagesByChatId(chatId);
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String chatName = chatRepository.getChatById(chatId).getName();
        String subjectName = subjectRepository.getSubjectById(subjectId).getName();
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        model.addAttribute("login", userDetails.getUsername());
        model.addAttribute("chatName", chatName);
        model.addAttribute("subjectName", subjectName);
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
    }

    @RequestMapping(value = "subjects/{subjectId}/chats/{chatId}/messages/get/{lastMessageTime}", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public List<Message> get(Model model, @PathVariable Integer subjectId, @PathVariable Integer chatId, @PathVariable String lastMessageTime) {

        Timestamp lastMessageTimeStamp = Timestamp.valueOf(lastMessageTime);
        List<Message> newMessages = messageRepository.getNewMessages(chatId, lastMessageTimeStamp);
        return newMessages;
    }

/*    @RequestMapping(value = "/messages/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String removeMessage(@PathVariable Integer id){
        this.messageRepository.removeMessage(id);
        return "redirect:/messages";
    }*/
}
