package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.bindings.WorkoutEditBinding;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.ExerciseDetailsView;
import com.ex.FitApp.models.views.ExerciseWorkoutEditView;
import com.ex.FitApp.models.views.WorkoutDetailsView;
import com.ex.FitApp.models.views.WorkoutView;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.WorkoutRepository;
import com.ex.FitApp.services.ExerciseService;
import com.ex.FitApp.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
    public WorkoutEntity addWorkout(WorkoutAddBinding workoutModel, String username) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);
        UserEntity user = this.userRepository.findByUsername(username).orElse(null);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new HashSet<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }
        workoutEntity.setUserEntity(user);

        this.workoutRepository.save(workoutEntity);
        return workoutEntity;
    }

    @Override
    public WorkoutEntity bindingToEntity(WorkoutAddBinding workoutModel) {
        WorkoutEntity workoutEntity=this.modelMapper.map(workoutModel,WorkoutEntity.class);

        if(workoutEntity.getExercises().size() !=0){
            workoutEntity.setExercises(new HashSet<>());
        }

        for (String exercise : workoutModel.getExercisesNames()) {
            workoutEntity.getExercises().add(this.exerciseService.findByExName(exercise));
        }
        return workoutEntity;
    }

    @Override
    public List<WorkoutView> getAllWorkoutsByUsername(String username) {

        List<WorkoutView> workouts=new ArrayList<>();
        for (WorkoutEntity workoutEntity :this.userRepository.findAllUsersWorkout(username) ) {
            WorkoutView view=this.modelMapper.map(workoutEntity,WorkoutView.class);
            view.setExercises(workoutEntity.getExercises().stream().map(ExerciseEntity::getExName).collect(Collectors.toList()));
            workouts.add(view);
        }
        return workouts;
    }

    @Override
    public void deleteById(Long workoutId) {
        this.workoutRepository.deleteById(workoutId);
        System.out.println();
    }

    public WorkoutDetailsView findById(Long workoutId) {
        WorkoutDetailsView view= this.modelMapper.map(workoutRepository.findById(workoutId).orElse(null), WorkoutDetailsView.class);
        return view;
    }
    //could be done with generic
    @Override
    public List<ExerciseDetailsView> findAllExercisesInAWorkout(Long workoutId) {
        return this.workoutRepository.findAllExerciseFromWorkoutId(workoutId)
                .stream()
                .map(exerciseEntity -> modelMapper.map(exerciseEntity,ExerciseDetailsView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseWorkoutEditView> findAllExercisesInAWorkoutWithIds(Long workoutId) {
        return this.workoutRepository.findAllExerciseFromWorkoutId(workoutId)
                .stream()
                .map(exerciseEntity -> modelMapper.map(exerciseEntity,ExerciseWorkoutEditView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkoutView> getAllWorkouts() {
       return this.workoutRepository.findAll().stream()
                .map(workoutEntity -> modelMapper.map(workoutEntity,WorkoutView.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfLoggedUserIsTheOwner(String username, Long workoutId) {
        AtomicBoolean isIt = new AtomicBoolean(false);
        UserEntity userEntity =  this.userRepository.findByUsername(username).orElse(null);

        if(userEntity == null){
            return isIt.get();
        }

        userEntity.getWorkouts().forEach(workoutEntity -> {
            if(workoutEntity.getId() == workoutId){
                isIt.set(true);
            }});

        return isIt.get();
    }

    @Override
    public void deleteWorkoutFromUser(String username, Long workoutId) {
        UserEntity userEntity =  this.userRepository.findByUsername(username).orElse(null);
        WorkoutEntity workoutEntity = this.workoutRepository.findById(workoutId).orElse(null);

        if(userEntity==null || workoutEntity==null) {
            return;
        }

        userEntity.getWorkouts().remove(workoutEntity);
        this.workoutRepository.delete(workoutEntity);
    }

    @Override
    public void editWorkout(WorkoutEditBinding workoutNew, String username, Long workoutId) {
        this.workoutRepository.findById(workoutId)
                .map(workout -> {
                    workout.setDescription(workoutNew.getDescription());
                    workout.setDuration(workoutNew.getDuration());
                    workout.setWorkoutName(workoutNew.getWorkoutName());
//                    workout.setExercises(workoutNew.getExercisesNames());
                    return this.workoutRepository.save(workout);
                });
    }

    @Override
    public WorkoutEditBinding preSetBindingValue(WorkoutEditBinding workoutBinding, WorkoutDetailsView workoutView) {
        return this.modelMapper.map(workoutView,WorkoutEditBinding.class);
    }

    @Override
    public boolean removeExercise(Long workoutId, Long exerciseId) {
        WorkoutEntity workout = this.workoutRepository.findById(workoutId).orElse(null);
        if(workout == null){
            return false;
        }
        ExerciseEntity exercise =this.exerciseService.getExerciseById(exerciseId).orElse(null);
        if(exercise == null){
            return false;
        }
        Set<ExerciseEntity> exercises = workout.getExercises();
        for (ExerciseEntity exerciseEntity : exercises) {
            if(exercise.getExName().equals(exerciseEntity.getExName())){
                exercises.remove(exerciseEntity);
                break;
            }
        }

        workout.setExercises(exercises);
        this.workoutRepository.save(workout);
        return true;
    }

    @Override
    public boolean addExercise(Long workoutId, Long exerciseId) {
        WorkoutEntity workout = this.workoutRepository.findById(workoutId).orElse(null);
        if(workout == null){
            return false;
        }
        ExerciseEntity exercise =this.exerciseService.getExerciseById(exerciseId).orElse(null);
        if(exercise == null){
            return false;
        }
        Set<ExerciseEntity> exercises = workout.getExercises();
        for (ExerciseEntity exerciseEntity : exercises) {
            if(exercise.getExName().equals(exerciseEntity.getExName())){
                return false;
            }
        }

        workout.getExercises().add(exercise);
        this.workoutRepository.save(workout);
        return true;
    }

    @Override
    public List<ExerciseWorkoutEditView> findExercisesThatAreNotInThisWorkout(Long workoutId) {
        List<ExerciseWorkoutEditView> all= this.exerciseService.getAll();
        List<ExerciseDetailsView> my= this.findAllExercisesInAWorkout(workoutId);

        for (ExerciseDetailsView mine : my) {
            all.removeIf(al -> (al.getExName().equals(mine.getExName())));
        }
        return all.stream().map(exerciseEntity -> this.modelMapper.map(exerciseEntity,ExerciseWorkoutEditView.class)).collect(Collectors.toList());
    }

}
