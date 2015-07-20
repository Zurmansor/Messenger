package com.sigmaukraine.messenger.controller;


import com.sigmaukraine.messenger.domain.Subject;
import com.sigmaukraine.messenger.domain.User;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;
    private UserValidator userValidator;

    public UserController() {
    }

    @Autowired
    public UserController(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
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

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String editUser(@PathVariable Integer id, Model model) {
        User user = this.userRepository.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "users/edit";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String editUser(@PathVariable Integer id, @ModelAttribute("user") User user, BindingResult bindingResult) {
        this.userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }


        this.userRepository.editUser(id, user);
        return "redirect:/users";
    }

}
