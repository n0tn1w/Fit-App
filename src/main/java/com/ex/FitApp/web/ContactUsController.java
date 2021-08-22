package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.ContactUsAddBinding;
import com.ex.FitApp.services.ContactUsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @ModelAttribute("contactUsModel")
    public ContactUsAddBinding createBindingModel() {
        return new ContactUsAddBinding();
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public String contactUs(){
        return "contact-us";
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public String contactUsPost(@Valid ContactUsAddBinding contactUsModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("contactUsModel", contactUsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "contactUsModel", bindingResult);
            return "redirect:/contact-us";
        }

        System.out.println();
        this.contactUsService.addMessage(contactUsModel);
        return "redirect:/";
    }



}
