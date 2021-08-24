package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.services.ExerciseService;
import com.ex.FitApp.services.UserService;
import com.ex.FitApp.services.WorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, UserService userService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    private String add(Model model){
        if(!model.containsAttribute("workoutModel")) {
            model.addAttribute("workoutModel", new WorkoutAddBinding());
            model.addAttribute("exerciseModel", this.exerciseService.getAllExercisesBindings());
        }
        return "workout-add";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    private String addWorkout(@Valid @ModelAttribute("workoutModel") WorkoutAddBinding workoutModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails principal){

        if (this.workoutService.findByWorkoutName(workoutModel.getWorkoutName()) != null) {
            bindingResult.rejectValue("workoutName", "workoutModel", "A workout with this name already exists.");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("workoutModel", workoutModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "workoutModel", bindingResult);
            redirectAttributes.addFlashAttribute("exerciseModel",this.exerciseService.getAllExercisesBindings());
            return "redirect:/workout/add";
        }

//        this.userService.setWorkout(principal.getUsername(),
//                this.workoutService.addWorkout(workoutModel,principal.getUsername()));

        this.workoutService.addWorkout(workoutModel,principal.getUsername());
        return "redirect:/workout/all";
    }

    //all workouts for only 1 user
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/all")
//    public String getALlWorkouts(Model model,@AuthenticationPrincipal UserDetails principal){
//        model.addAttribute("workouts", workoutService.getAllWorkouts(principal.getUsername()));
//        return "workout-all";
//    }
        @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public String getALlWorkouts(Model model){
        model.addAttribute("workouts", workoutService.getAllWorkouts());
        return "workout-all";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/details/{id}")
    public String getDetailsForWorkout(Model model,@PathVariable("id") Long workoutId,@AuthenticationPrincipal UserDetails principal){
        model.addAttribute("workout", this.workoutService.findById(workoutId));
        model.addAttribute("exercises",this.workoutService.findAllExercisesInAWorkout(workoutId));

        model.addAttribute("isTheLoggedInUserOwner",this.workoutService.checkIfLoggedUserIsTheOwner(principal.getUsername(),workoutId));

        return "workout-details";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/details/delete/{id}")
    public String deleteDetails(@PathVariable("id") Long workoutId){
        this.workoutService.deleteById(workoutId);
        return "redirect:/workout/all";
    }
}
