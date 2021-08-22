package com.ex.FitApp.models.views;

import com.ex.FitApp.models.entities.enums.BodyType;

import javax.persistence.Column;

public class UserControlPanelView {
    private String username;
    private String firstName;
    private String lastName;
    private String email;


    public UserControlPanelView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
