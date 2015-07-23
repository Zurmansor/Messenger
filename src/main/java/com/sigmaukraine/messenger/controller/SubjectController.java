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

import java.util.HashMap;
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
        HashMap<String, String> breadcrumbs = new HashMap<String, String>();
        breadcrumbs.put("title.subjects", "#");

        model.addAttribute("subjects", subjects);
        model.addAttribute("breadcrumbs", breadcrumbs);
        return "subjects/list";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String addSubject(Model model) {
        HashMap<String, String> breadcrumbs = new HashMap<String, String>();
        breadcrumbs.put("title.add_subject", "#");
        breadcrumbs.put("title.subjects", "/subjects");

        model.addAttribute("breadcrumbs", breadcrumbs);
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

    @RequestMapping(value = "/subjects/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String editSubject(@PathVariable Integer id, Model model) {
        Subject subject = this.subjectRepository.getSubjectById(id);
        if (subject == null) {
            return "redirect:/subjects";
        }

        HashMap<String, String> breadcrumbs = new HashMap<String, String>();
        breadcrumbs.put("title.edit_subject", "#");
        breadcrumbs.put("title.subjects", "/subjects");

        model.addAttribute("subject", subject);
        model.addAttribute("breadcrumbs", breadcrumbs);
        return "subjects/edit";
    }

    @RequestMapping(value = "/subjects/edit/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String editSubject(@PathVariable Integer id, @ModelAttribute("subject") Subject subject, BindingResult bindingResult) {
        this.subjectValidator.validate(subject, bindingResult);

        if (bindingResult.hasErrors()) {
            return "subjects/edit";
        }

        Subject checkSubject = subjectRepository.getSubjectByName(subject.getName());
        if (checkSubject != null && !checkSubject.getId().equals(id)) {
            bindingResult.rejectValue("name", "invalid.name", "Subject with this name already exist");
            return "subjects/edit";
        }
        this.subjectRepository.editSubject(id, subject);
        return "redirect:/subjects";
    }


}
