package com.sigmaukraine.messenger.controller;

import com.sigmaukraine.messenger.breadcrumbs.Breadcrumbs;
import com.sigmaukraine.messenger.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController {

    private StatisticRepository statisticRepository;

    public StatisticController() {
    }

    @Autowired
    public StatisticController(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String statistic(Model model) {
        List<Breadcrumbs> breadcrumbs = new ArrayList<Breadcrumbs>();
        breadcrumbs.add(new Breadcrumbs("nav.statistic", "#"));

        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("userCount", this.statisticRepository.getUsersCount());
        model.addAttribute("subjectCount", this.statisticRepository.getSubjectsCount());
        model.addAttribute("chatstCount", this.statisticRepository.getChatsCount());
        model.addAttribute("messagesCount", this.statisticRepository.getMessagesCount());
        return "statistics/statistic";
    }
}
