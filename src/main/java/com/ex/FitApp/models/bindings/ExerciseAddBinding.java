package com.ex.FitApp.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ExerciseAddBinding {
    //    private String name;
    //    private int sets;
    //    private double weights;
    //    private BodyGroup bodyGroup;
    //    private String description;

    @NotNull
    @Length(min = 4,max = 30,message = "Name should be between 4 and 30 characters.")
    private String exName;
    @NotNull
    @Min(value = 1,message = "Number of sets should at least 1")
    private int sets;
    @NotNull
    @Min(value = 0,message = "Weight should be a positive number.")
    private double weight;
    @NotNull
    private String imageUrl;
    @NotNull
    private String bodyGroup;

    private String description;

    public ExerciseAddBinding() {
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBodyGroup() {
        return bodyGroup;
    }

    public void setBodyGroup(String bodyGroup) {
        this.bodyGroup = bodyGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
