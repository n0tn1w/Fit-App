package com.ex.FitApp.web;

import com.ex.FitApp.services.ContactUsService;
import com.ex.FitApp.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control-panel")
public class ControlPanelController {

    private final UserService userService;
    private final ContactUsService contactUsService;

    public ControlPanelController(UserService userService, ContactUsService contactUsService) {
        this.userService = userService;
        this.contactUsService = contactUsService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users-details")
    public String getControlPanel(Model model){
        if(!model.containsAttribute("usersDetails")) {
            model.addAttribute("usersDetails", userService.getAllUsersDetails());
        }
        return "control-panel/users-details";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/messages-details")
    public String getMessageDetails(Model model){
        if(!model.containsAttribute("messageDetails")) {
            model.addAttribute("messageDetails", contactUsService.getAllMessagesDetails());
        }
        return "control-panel/messages-details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/messages-details/delete/{id}")
    public String deleteMessageDetails(@PathVariable("id") Long messageId){
        this.contactUsService.deleteById(messageId);
        return "redirect:/control-panel/messages-details";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/messages-details/delete/all")
    public String deleteAllMessageDetails(){
        this.contactUsService.deleteAllMessages();
        return "redirect:/control-panel/messages-details";
    }


}
