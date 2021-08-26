package com.ex.FitApp.web;

import com.ex.FitApp.file.exception.FileStorageException;
import com.ex.FitApp.models.bindings.UserUsernameUpdateBinding;
import com.ex.FitApp.models.bindings.WorkoutEditBinding;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.views.UserProfileView;
import com.ex.FitApp.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-profile")
    public String getUserProfile(Principal principal,Model model) {

        UserProfileView user = this.userService.getUserProfile(principal.getName());
        model.addAttribute("user", user);

        return "profile-details";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/upload-picture")
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, Principal principal)
            throws FileStorageException, IOException {
        this.userService.uploadProfilePicture(principal.getName(), file);
        return "redirect:/users/my-profile";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-username")
    public String updateUsername(@Valid @ModelAttribute("user") UserUsernameUpdateBinding user,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetails principal){
        //test if username not taken
        if(this.userService.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("Username", "user", "A workout with this name already exists.");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "user", bindingResult);
            redirectAttributes.addFlashAttribute("didYouPutWrongInput", true);
            return "redirect:/users/my-profile";
        }

        this.userService.updateUsername(principal.getUsername(), user);
//        UserEntity userEntity = this.userService.updateUsername(principal.getUsername(), user);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, userEntity.getPassword(),userEntity.ge);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/users/my-profile";
    }
}
