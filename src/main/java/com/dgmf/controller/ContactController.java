package com.dgmf.controller;

import com.dgmf.entity.Contact;
import com.dgmf.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ContactController {
    // Already Done by "@Slf4j" Lombok Annotation
    // private static Logger log = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;


    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    /*// To Save the User Form Message
    // Can Use "@PostMapping("/saveMsg")" Instead
    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(
            @RequestParam String name,
            @RequestParam String mobileNum,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message
    ) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);

        return new ModelAndView("redirect:/contact");
    }*/

    // To Save the User Form Message
    // Can Use "@PostMapping("/saveMsg")" Instead
    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(Contact contact) {
        contactService.saveMessageDetails(contact);

        return new ModelAndView("redirect:/contact");
    }
}
