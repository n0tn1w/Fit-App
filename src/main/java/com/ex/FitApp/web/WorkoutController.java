package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.bindings.WorkoutEditBinding;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.WorkoutDetailsView;
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
    public String deleteDetails(@PathVariable("id") Long workoutId,@AuthenticationPrincipal UserDetails principal){
        if(this.workoutService.checkIfLoggedUserIsTheOwner(principal.getUsername(),workoutId)) {
            this.workoutService.deleteById(workoutId);
            return "redirect:/workout/all";
        }else {
            return "redirect:/home";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{id}")
    private String edit(Model model, @PathVariable("id") Long workoutId,@AuthenticationPrincipal UserDetails principal){
       if(this.workoutService.checkIfLoggedUserIsTheOwner(principal.getUsername(),workoutId)) {
           WorkoutDetailsView workoutView = this.workoutService.findById(workoutId);
           WorkoutEditBinding workoutBinding = new WorkoutEditBinding();

           if (!model.containsAttribute("workout")) {
               model.addAttribute("workout", this.workoutService.preSetBindingValue(workoutBinding, workoutView));
           }
           model.addAttribute("wkId", workoutId);
           model.addAttribute("exercises", this.workoutService.findAllExercisesInAWorkoutWithIds(workoutId));
           model.addAttribute("exercisesAll", this.workoutService.findExercisesThatAreNotInThisWorkout(workoutId));

           return "workout-edit";
       }else {
           return "redirect:/home";
       }
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/{id}")
    //should be PUT but html is shit
    private String editWorkout(@Valid @ModelAttribute("workout") WorkoutEditBinding workout,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal UserDetails principal,
                               @PathVariable("id") Long workoutId){
        String workoutIdString = workoutId.toString();

        if(this.workoutService.findByWorkoutName(workout.getWorkoutName()) != null && !workout.getWorkoutName().equals(this.workoutService.findById(workoutId).getWorkoutName())) {
            bindingResult.rejectValue("workoutName", "workout", "A workout with this name already exists.");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("workout", workout);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + "workout", bindingResult);
            redirectAttributes.addAttribute("exercises",this.workoutService.findAllExercisesInAWorkoutWithIds(workoutId));
            return "redirect:/workout/edit/"+workoutId;
        }

        this.workoutService.editWorkout(workout,principal.getUsername(),workoutId);

        return "redirect:/workout/details/"+workoutId;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{wkId}/exercise-delete/{exerciseId}")
    public String deleteEditExercise(@PathVariable("wkId") Long workoutId,@PathVariable("exerciseId") Long exerciseId,@AuthenticationPrincipal UserDetails principal){
        if(this.workoutService.checkIfLoggedUserIsTheOwner(principal.getUsername(),workoutId)) {
            if(this.workoutService.findAllExercisesInAWorkoutWithIds(workoutId).size()>1) {
                this.workoutService.removeExercise(workoutId, exerciseId);
                return "redirect:/workout/edit/" + workoutId;
            }else{
                return "redirect:/workout/edit/" + workoutId;
            }
        }else {
            return "redirect:/home";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{wkId}/exercise-add/{exerciseId}")
    public String addEditExercise(@PathVariable("wkId") Long workoutId,@PathVariable("exerciseId") Long exerciseId,@AuthenticationPrincipal UserDetails principal){
        if(this.workoutService.checkIfLoggedUserIsTheOwner(principal.getUsername(),workoutId)) {
            this.workoutService.addExercise(workoutId, exerciseId);
            return "redirect:/workout/edit/" + workoutId;
        }else {
            return "redirect:/home";
        }
    }
}
