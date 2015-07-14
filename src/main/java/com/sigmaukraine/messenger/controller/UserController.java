package com.sigmaukraine.messenger.controller;


import com.sigmaukraine.messenger.domain.User;
import com.sigmaukraine.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController() {
    }

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String list(Model model) {
        List<User> users = this.userRepository.listAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping(value = "/users/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String removeUser(@PathVariable Integer id){
        this.userRepository.removeUser(id);
        return "redirect:/users";
    }
}
