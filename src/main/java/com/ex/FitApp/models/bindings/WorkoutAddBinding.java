package com.ex.FitApp.models.bindings;

import com.ex.FitApp.models.entities.ExerciseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class WorkoutAddBinding {
    //    private String name;
    //    private int duration;
    //    private String description;

    @NotNull
    @Length(min = 4,max = 30,message = "Name should be between 4 and 30 characters.")
    private String workoutName;

    @NotNull
    @Min(value = 1,message = "Duration should be at least 1 minute.")
    @Max(value = 1000000,message = "Duration shouldn't be more than 1 000 000 minutes.")
    private int duration;

    private String description;

    @NotNull(message = "Select one or more exercises")
    private List<String> exercisesNames;

    public WorkoutAddBinding() {
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
