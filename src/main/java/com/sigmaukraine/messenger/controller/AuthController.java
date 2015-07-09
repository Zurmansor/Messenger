package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.User;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.utils.Encryption;
import com.sigmaukraine.messenger.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.NoSuchAlgorithmException;

@Controller
public class AuthController {

    private UserRepository userRepository;
    private UserValidator userValidator;

    public AuthController() {
    }

    @Autowired
    public AuthController(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        this.userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            return "registration";
        }

        if (userRepository.getUserByLogin(user.getLogin()) != null) {
            bindingResult.rejectValue("login", "invalid.login", "User with this login already exist");
            return "registration";
        }

        try {
            Encryption encryption = new Encryption();
            user.setPassword(encryption.md5Encode(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        user.setRoleId(2);
//        model.addAttribute("user", new User());
        this.userRepository.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        return "sign_in";
    }

//    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
//    public String signIn(@ModelAttribute("user") User user, BindingResult bindingResult) {
//
//        if (userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword()) != null) {
//
//        } else {
//            bindingResult.rejectValue("login", "invalid.login", "Wrong login or password");
//            return "sign_in";
//        }
//
//
//    }

}
