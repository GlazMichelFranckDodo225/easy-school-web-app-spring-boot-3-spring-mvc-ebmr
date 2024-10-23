package com.dgmf.controller;

import com.dgmf.entity.EazyClass;
import com.dgmf.entity.Person;
import com.dgmf.repository.EazyClassRepository;
import com.dgmf.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final EazyClassRepository eazyClassRepository;
    private final PersonRepository personRepository;
    
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("eazyClasses",eazyClasses);
        modelAndView.addObject("eazyClass", new EazyClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(
            Model model, @ModelAttribute("eazyClass") EazyClass eazyClass
    ) {
        eazyClassRepository.save(eazyClass);
        ModelAndView modelAndView =
                new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        for(Person person : eazyClass.get().getPersons()){
            person.setEazyClass(null);
            personRepository.save(person);
        }
        eazyClassRepository.deleteById(id);
        ModelAndView modelAndView =
                new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(
            Model model,
            @RequestParam int classId
            /*HttpSession session,
            @RequestParam(value = "error", required = false) String error*/
    ) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        /*Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
        modelAndView.addObject("eazyClass",eazyClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("eazyClass",eazyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }*/
        return modelAndView;
    }
}
