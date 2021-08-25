package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.bindings.ExerciseWorkoutAddBindingModel;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.views.ExerciseWorkoutEditView;
import com.ex.FitApp.repositories.ExerciseRepository;
import com.ex.FitApp.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ModelMapper modelMapper, ExerciseRepository exerciseRepository) {
        this.modelMapper = modelMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseEntity findByExName(String name) {
        return this.exerciseRepository.findByExName(name).orElse(null);
    }

    @Override
    public void addExercise(ExerciseAddBinding exerciseAddBinding) {
        ExerciseEntity exerciseEntity=this.modelMapper.map(exerciseAddBinding,ExerciseEntity.class);
        this.exerciseRepository.save(exerciseEntity);
    }

    @Override
    public List<String> getAllNames() {
        return this.exerciseRepository.getAllByName();
    }

    @Override
    public List<ExerciseWorkoutAddBindingModel> getAllExercisesBindings() {
        return this.exerciseRepository.findAll().stream()
                .map(exerciseEntity -> this.modelMapper.map(exerciseEntity,ExerciseWorkoutAddBindingModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExerciseEntity> getExerciseById(Long exerciseId) {
        return this.exerciseRepository.findById(exerciseId);
    }

    @Override
    public List<ExerciseWorkoutEditView> getAll() {
        return this.exerciseRepository.findAll().stream()
                .map(exerciseEntity -> modelMapper.map(exerciseEntity,ExerciseWorkoutEditView.class))
                .collect(Collectors.toList());
    }
}
