package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.ExerciseWorkoutAddBindingModel;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.views.ExerciseWorkoutEditView;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    ExerciseEntity findByExName(String name);

    void addExercise(ExerciseAddBinding exerciseAddBinding);

    List<String> getAllNames();

    List<ExerciseWorkoutAddBindingModel> getAllExercisesBindings();

    Optional<ExerciseEntity> getExerciseById(Long exerciseId);

    List<ExerciseWorkoutEditView> getAll();
}
