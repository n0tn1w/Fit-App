package com.ex.FitApp.models.entities;

import com.ex.FitApp.models.entities.enums.BodyGroup;

import javax.persistence.*;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity{

    @Column(nullable = false,name = "exercise_name")
    private String exName;

    @Column(nullable = false)
    private int sets;

    @Column(nullable = false)
    private double weights;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_group",nullable = false)
    private BodyGroup bodyGroup;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;



    public ExerciseEntity() {
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

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public BodyGroup getBodyGroup() {
        return bodyGroup;
    }

    public void setBodyGroup(BodyGroup bodyGroup) {
        this.bodyGroup = bodyGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
