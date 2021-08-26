package com.ex.FitApp.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserUsernameUpdateBinding {
    @NotNull
    @Length(min = 4, max = 30, message = "Username length must be between 4 and 30 characters.")
    private String username;

    public UserUsernameUpdateBinding() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
