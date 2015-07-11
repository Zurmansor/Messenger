package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.domain.Subject;
import com.sigmaukraine.messenger.repository.SubjectRepository;
import com.sigmaukraine.messenger.repository.UserRepository;
import com.sigmaukraine.messenger.validation.SubjectValidator;
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
public class SubjectController {

    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private SubjectValidator subjectValidator;

    public SubjectController() {
    }

    @Autowired
    public SubjectController(SubjectRepository subjectRepository, UserRepository userRepository, SubjectValidator subjectValidator) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.subjectValidator = subjectValidator;
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String list(Model model) {
        List<Subject> subjects = this.subjectRepository.listAll();
        model.addAttribute("subjects", subjects);
        return "subjects/list";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String addSubject(Model model) {
        model.addAttribute("subject", new Subject());

        return "subjects/add";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String addSubject(@ModelAttribute("subject") Subject subject, BindingResult bindingResult) {
        this.subjectValidator.validate(subject, bindingResult);

        if (bindingResult.hasErrors()) {
            return "subjects/add";
        }

        if (subjectRepository.getSubjectByName(subject.getName()) != null) {
            bindingResult.rejectValue("name", "invalid.name", "Subject with this name already exist");
            return "subjects/add";
        }

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sigmaukraine.messenger.domain.User user = userRepository.getUserByLogin(userDetails.getUsername());
        subject.setCreatedBy(user.getId());
        this.subjectRepository.addSubject(subject);
        return "redirect:/subjects";
    }

    @RequestMapping(value = "/subjects/remove/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String removeSubject(@PathVariable Integer id){
        this.subjectRepository.removeSubject(id);
        return "redirect:/subjects";
    }
}
