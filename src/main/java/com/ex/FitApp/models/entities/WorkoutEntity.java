package com.ex.FitApp.models.entities;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity{

    @Column(nullable = false,name = "workout_name")
    private String workoutName;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ExerciseEntity> exercises;

    @ManyToOne()
    private UserEntity userEntity;


    public WorkoutEntity() {
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
