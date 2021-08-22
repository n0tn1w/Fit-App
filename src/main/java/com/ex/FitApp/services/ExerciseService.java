package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.ExerciseWorkoutAddBindingModel;
import com.ex.FitApp.models.entities.ExerciseEntity;

import java.util.List;

public interface ExerciseService {
    ExerciseEntity findByExName(String name);

    void addExercise(ExerciseAddBinding exerciseAddBinding);

    List<String> getAllNames();

    List<ExerciseWorkoutAddBindingModel> getAllExercisesBindings();
}
