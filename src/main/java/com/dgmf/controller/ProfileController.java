package com.dgmf.controller;

import com.dgmf.entity.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model) {
        Profile profile = new Profile();
        ModelAndView modelAndView = new ModelAndView("profile.html");
        // Send Profile Object to the UI
        modelAndView.addObject("profile", profile);

        return modelAndView;
    }
}
