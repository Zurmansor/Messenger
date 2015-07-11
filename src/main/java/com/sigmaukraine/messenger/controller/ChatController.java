package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Chat;
import com.sigmaukraine.messenger.domain.Message;
import com.sigmaukraine.messenger.repository.ChatRepository;
import com.sigmaukraine.messenger.repository.MessageRepository;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.validation.ChatValidator;
import com.sigmaukraine.messenger.validation.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ChatController {

    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private ChatValidator chatValidator;

    public ChatController() {
    }

    @Autowired
    public ChatController(ChatRepository chatRepository, UserRepository userRepository, ChatValidator chatValidator) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatValidator = chatValidator;
    }

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String list(Model model) {
        List<Chat> chats= this.chatRepository.listAll();
        model.addAttribute("chats", chats);
        return "chats/list";
    }

    @RequestMapping(value = "/chats/add", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String addChat(Model model) {
        model.addAttribute("chat", new Chat());

        return "chats/add";
    }

    @RequestMapping(value = "/chats/add", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addChat(@ModelAttribute("chat") Chat chat, BindingResult bindingResult) {
        this.chatValidator.validate(chat, bindingResult);

        if (bindingResult.hasErrors()) {
            return "chats/add";
        }


        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sigmaukraine.messenger.domain.User user = userRepository.getUserByLogin(userDetails.getUsername());
        chat.setCreatedBy(user.getId());
//        FIXME: themId
        chat.setThemId(1);

        this.chatRepository.addChat(chat);
        return "redirect:/chats";
    }

/*    @RequestMapping(value = "/chats/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String removeChat(@PathVariable Integer id){
        this.chatRepository.removeChat(id);
        return "redirect:/chats";
    }*/
}
