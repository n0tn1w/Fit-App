package com.ex.FitApp.models.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ContactUsAddBinding {

    //@Column(nullable = false)
    //    private String username;
    //    @Column(nullable = false)
    //    private String email;
    //    @Column(nullable = false)
    //    private String message;

    @NotNull
    @Length(min = 4, max = 30, message = "Username length must be between 4 and 30 characters.")
    private String username;

    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Length(min = 12, max = 400, message = "Message must be between 10 and 400 characters long")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
