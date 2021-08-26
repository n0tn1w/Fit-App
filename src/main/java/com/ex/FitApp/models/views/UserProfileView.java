package com.ex.FitApp.models.views;

import com.ex.FitApp.file.model.DBFile;
import com.ex.FitApp.models.entities.UserRoleEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.entities.enums.BodyType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserProfileView {
    private String firstName;
    private String lastName;
    private String username;

    private String email;
    private int age;
    private double weight;
    private double height;

    private String bodyType;

    private String profilePictureString;

    public UserProfileView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getProfilePictureString() {
        return profilePictureString;
    }

    public void setProfilePictureString(String profilePictureString) {
        this.profilePictureString = profilePictureString;
    }
}
