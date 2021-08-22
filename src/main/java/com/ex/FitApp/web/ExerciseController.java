package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.services.ExerciseService;
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
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @ModelAttribute("exerciseModel")
    public ExerciseAddBinding createBindingModel() {
        return new ExerciseAddBinding();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    private String addExercise(){
        return "exercise-add";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    private String addExercisePost(@Valid ExerciseAddBinding exerciseModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (this.exerciseService.findByExName(exerciseModel.getExName()) != null) {
            bindingResult.rejectValue("exName", "error.exerciseModel", "A exercise with this name already exists.");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseModel", exerciseModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "exerciseModel", bindingResult);
            return "redirect:/exercise/add";
        }

        this.exerciseService.addExercise(exerciseModel);
        return "redirect:/home";
    }
}
