package com.ex.FitApp.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WorkoutEditBinding {
    @NotNull
    @Length(min = 4,max = 30,message = "Name should be between 4 and 30 characters.")
    private String workoutName;

    @NotNull
    @Min(value = 1,message = "Duration should be at least 1 minute.")
    @Max(value = 1000000,message = "Duration shouldn't be more than 1 000 000 minutes.")
    private int duration;

    private String description;

    private List<String> exercisesNames;

    public WorkoutEditBinding() {
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

    public List<String> getExercisesNames() {
        return exercisesNames;
    }

    public void setExercisesNames(List<String> exercisesNames) {
        this.exercisesNames = exercisesNames;
    }
}
