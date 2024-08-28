package com.dgmf.controller;

import com.dgmf.entity.Person;
import com.dgmf.entity.Profile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Period;

@Slf4j
@Controller
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model, HttpSession session) {
        // Fetch Person DB Details from the Session
        Person person = (Person) session.getAttribute("loggedInPerson");
        // Set Person Details to His Profile
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());
        // Check If Address Details is Present into Person Object in Order to
        // Set Also Address Details to His Profile
        if(person.getAddress() !=null && person.getAddress().getAddressId()>0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html");
        // Send Profile Object to the UI
        modelAndView.addObject("profile", profile);

        return modelAndView;
    }
}
