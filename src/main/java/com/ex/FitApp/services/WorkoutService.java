package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.ExerciseDetailsView;
import com.ex.FitApp.models.views.WorkoutDetailsView;
import com.ex.FitApp.models.views.WorkoutView;

import java.util.List;

public interface WorkoutService {
    WorkoutEntity findByWorkoutName(String workoutName);

    void addWorkout(WorkoutAddBinding workoutModel);

    WorkoutEntity bindingToEntity(WorkoutAddBinding workoutModel);

    List<WorkoutView> getAllWorkouts(String username);

    void deleteById(Long messageId);

    WorkoutDetailsView findById(Long workoutId);

    List<ExerciseDetailsView>findAllExercisesInAWorkout(Long workoutId);
}
