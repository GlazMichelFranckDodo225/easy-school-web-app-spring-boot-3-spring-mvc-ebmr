package com.dgmf.controller;

import com.dgmf.entity.Contact;
import com.dgmf.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String displayContactPage(Model model) {
        // Adding a Blank Contact Object to the Model witch will Be Available
        // into the Contact Form
        model.addAttribute("contact", new Contact());

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
    public String saveMessage(
            // Now, Here is a Fully Filled Contact Object into the Model witch
            // Should Match all Validations Available inside the Contact Entity
            @Valid @ModelAttribute("contact") Contact contact, Errors errors
    ) {
        // In Case of Any Error
        if(errors.hasErrors()) {
            log.error("Contact Form Validation Failed Due to : " + errors.toString());

            // Display the same Page
            return "contact.html";
        }

        // Without Error Perform the Business Logic
        contactService.saveMessageDetails(contact);

        // Redirect to Handler Method
        return "redirect:/contact";
    }
}
