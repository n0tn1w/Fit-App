package com.ex.FitApp.models.bindings;

import com.ex.FitApp.validators.FieldMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegisterBindingModel {
    //    private int age;
    //    private double weight;
    //    private double height;
    @NotNull
    @Length(min = 4,max = 30,message = "Firstname should be between 4 and 30 characters.")
    private String firstName;

    @NotNull
    @Length(min = 4,max = 30,message = "Lastname should be between 4 and 30 characters.")
    private String lastName;

    @NotNull
    @Length(min = 4, max = 30, message = "Username length must be between 4 and 30 characters.")
    private String username;

    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Length(min = 4, max = 30, message = "Password length must be between 4 and 30 characters.")
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Min(value = 0, message = "Age should be a positive number.")
    private int age;

    @NotNull
    @Min(value = 0,message = "Weight should be a positive number.")
    private double weight;

    @NotNull
    @Min(value = 0,message = "Height should be a positive number.")
    @Max(value = 4,message = "Incorrect height.")
    private double height;

    @NotNull
    private String bodyType;

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
}
