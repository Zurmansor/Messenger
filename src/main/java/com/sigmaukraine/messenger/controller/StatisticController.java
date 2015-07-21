package com.sigmaukraine.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController {

    public StatisticController () {
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String statistic(Model model) {
        return "statistics/statistic";
    }
}
