package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Chat;
import com.sigmaukraine.messenger.domain.Subject;
import com.sigmaukraine.messenger.repository.ChatRepository;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.validation.ChatValidator;
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
@RequestMapping(value = "/subjects/{subjectId}")
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
    public String list(Model model, @PathVariable Integer subjectId) {
        List<Chat> chats= this.chatRepository.getListChatsBySubjectId(subjectId);
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sigmaukraine.messenger.domain.User user = userRepository.getUserByLogin(userDetails.getUsername());
        model.addAttribute("chats", chats);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("user", user);
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
    public String addChat(@ModelAttribute("chat") Chat chat, BindingResult bindingResult, @PathVariable Integer subjectId) {
        this.chatValidator.validate(chat, bindingResult);

        //todo: fix base them
        //todo: unique base

        if (!chatRepository.isUnique(chat.getName(), subjectId)) {
            bindingResult.rejectValue("name", "invalid.name", "Chat with this name already exist");
            return "chats/add";
        }
        if (bindingResult.hasErrors()) {
            return "chats/add";
        }

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sigmaukraine.messenger.domain.User user = userRepository.getUserByLogin(userDetails.getUsername());
        chat.setCreatedBy(user.getId());
        chat.setSubjectId(subjectId);
        this.chatRepository.addChat(chat);
        return "redirect:/subjects/"+ subjectId +"/chats";
    }

    @RequestMapping(value = "/chats/{chatId}/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String editChat(@PathVariable Integer subjectId, @PathVariable Integer chatId, Model model) {
        Chat chat = this.chatRepository.getChatById(chatId);
        if (chat == null) {
            return "redirect:/subjects/"+ subjectId +"/chats";
        }
        model.addAttribute("chat", chat);
        return "chats/edit";
    }

    @RequestMapping(value = "/chats/{chatId}/edit/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String editChat(@PathVariable Integer subjectId, @PathVariable Integer chatId, @ModelAttribute("chat") Chat chat, BindingResult bindingResult) {
        this.chatValidator.validate(chat, bindingResult);

        if (bindingResult.hasErrors()) {
            return "chats/edit";
        }

        if (!chatRepository.isUnique(chat.getName(), chatId)) {
            bindingResult.rejectValue("name", "invalid.name", "Chat with this name already exist");
            return "chats/add";
        }
        this.chatRepository.editChat(chatId, chat);
        return "redirect:/subjects/"+ subjectId +"/chats";
    }

/*    @RequestMapping(value = "/chats/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String removeChat(@PathVariable Integer id){
        this.chatRepository.removeChat(id);
        return "redirect:/chats";
    }*/
}
