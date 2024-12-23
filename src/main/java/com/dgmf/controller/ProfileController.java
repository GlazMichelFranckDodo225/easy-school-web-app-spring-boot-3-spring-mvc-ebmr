package com.dgmf.controller;

import com.dgmf.entity.Address;
import com.dgmf.entity.Person;
import com.dgmf.entity.Profile;
import com.dgmf.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Period;

@Slf4j
// @Controller
@Controller("profileControllerBean")
@RequiredArgsConstructor
public class ProfileController {
    private final PersonRepository personRepository;

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

    @PostMapping(value = "/updateProfile")
    public String updateProfile(
            @Valid @ModelAttribute("profile") Profile profile,
            Errors errors,
            HttpSession session
    )
    {
        if(errors.hasErrors()){
            // Return the Same Page
            return "profile.html";
        }
        // Fetch Person Available into Session
        Person person = (Person) session.getAttribute("loggedInPerson");
        // Update Person Object Details Using Profile  Object
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() ==null || !(person.getAddress().getAddressId()>0)){
            // Create New Empty Address
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        Person savedPerson = personRepository.save(person);
        // Set Person Session Data with the Latest Data
        session.setAttribute("loggedInPerson", savedPerson);

        // Show User Profile Page
        return "redirect:/displayProfile";
    }
}
