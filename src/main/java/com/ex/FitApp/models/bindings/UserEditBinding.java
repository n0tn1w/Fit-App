package com.ex.FitApp.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserEditBinding {
    @NotNull
    @Length(min = 4,max = 30,message = "Firstname should be between 4 and 30 characters.")
    private String firstName;

    @NotNull
    @Length(min = 4,max = 30,message = "Lastname should be between 4 and 30 characters.")
    private String lastName;

    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Invalid email")
    @Length(max = 35, message = "Email length must be less than 36 characters.")
    private String email;

    @NotNull
    @Min(value = 0, message = "Age should be a positive number.")
    @Max(value = 1000, message = "Age should be bellow 1000.")
    private int age;

    @NotNull
    @Min(value = 0,message = "Weight should be a positive number.")
    @Max(value = 20000, message = "Weight should be bellow 20 000.")
    private double weight;

    @NotNull
    @Min(value = 0,message = "Height should be a positive number.")
    @Max(value = 4,message = "Weight should be bellow 4 meters.")
    private double height;

    @NotNull
    private String bodyType;

    public UserEditBinding() {
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
}

