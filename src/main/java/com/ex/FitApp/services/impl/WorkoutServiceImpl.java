package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.WorkoutDetailsView;
import com.ex.FitApp.models.views.WorkoutView;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.WorkoutRepository;
import com.ex.FitApp.services.ExerciseService;
import com.ex.FitApp.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final ModelMapper modelMapper;
    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    public WorkoutServiceImpl(ModelMapper modelMapper, WorkoutRepository workoutRepository, ExerciseService exerciseService, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
        ;
    }

    @Override
    public WorkoutEntity findByWorkoutName(String workoutName) {
        return this.workoutRepository.findByWorkoutName(workoutName).orElse(null);
    }

    @Override
    public void addWorkout(WorkoutAddBinding workoutModel) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new ArrayList<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }


        this.workoutRepository.save(workoutEntity);
    }

    @Override
    public WorkoutEntity bindingToEntity(WorkoutAddBinding workoutModel) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new ArrayList<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }
        return workoutEntity;
    }

    @Override
    public List<WorkoutView> getAllWorkouts(String username) {

        List<WorkoutView> workouts=new ArrayList<>();
        for (WorkoutEntity workoutEntity :this.userRepository.findAllUsersWorkout(username) ) {
            WorkoutView view=this.modelMapper.map(workoutEntity,WorkoutView.class);
            view.setExercises(workoutEntity.getExercises().stream().map(ExerciseEntity::getExName).collect(Collectors.toList()));
            workouts.add(view);
        }
        return workouts;
    }

    @Override
    public void deleteById(Long messageId) {
        this.workoutRepository.deleteById(messageId);
    }

    public WorkoutDetailsView findById(Long workoutId) {
        WorkoutDetailsView view= this.modelMapper.map(workoutRepository.findById(workoutId).orElse(null), WorkoutDetailsView.class);
        return view;
    }


}
