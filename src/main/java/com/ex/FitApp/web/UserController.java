package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String confirmRegisterForm(@Valid @ModelAttribute("registerModel") UserRegisterBindingModel registerModel,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(this.userService.findByUsername(registerModel.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.registerModel", "Username is already taken");
        }

        if (this.userService.findByEmail(registerModel.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.registerModel", "A profile with this email address already exists");
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", registerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "registerModel", bindingResult);
            return "/register";
        }

        //Successful registration
        this.userService.registerUser(registerModel);
        return "/home";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(RedirectAttributes attributes) {

        attributes.addFlashAttribute("incorrect", true);

        return "redirect:/users/login";
    }
}
