package com.ex.FitApp.web;

import com.ex.FitApp.services.CarouselService;
import com.ex.FitApp.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarouselService carouselService;
    private final UserService userService;

    public HomeController(CarouselService carouselService, UserService userService) {
        this.carouselService = carouselService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails principal){

        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());

//        model.addAttribute("workoutImages",userService.ge)

        return "home";
    }

    @GetMapping("/about-us")
    public String getAboutUs() {
        return "about-us";
    }
}
