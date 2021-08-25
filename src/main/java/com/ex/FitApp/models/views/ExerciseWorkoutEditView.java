package com.ex.FitApp.models.views;

public class ExerciseWorkoutEditView {
    private String id;
    private String exName;
    private int sets;
    private double weights;
    private String bodyGroup;

    public ExerciseWorkoutEditView() {
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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

    public String getBodyGroup() {
        return bodyGroup;
    }

    public void setBodyGroup(String bodyGroup) {
        this.bodyGroup = bodyGroup;
    }
}
