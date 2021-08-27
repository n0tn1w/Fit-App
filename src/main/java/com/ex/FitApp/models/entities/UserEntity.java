package com.ex.FitApp.models.entities;

import com.ex.FitApp.file.model.DBFile;
import com.ex.FitApp.models.entities.enums.BodyType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "username",nullable = false)
    private String username;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER, mappedBy = "userEntity")
    private Set<WorkoutEntity> workouts;

    @Email
    private String email;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double height;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorityEntity> authorities=new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "picture_id")
    private DBFile profilePicture;

    public UserEntity() {
    }


    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
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

    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public Set<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }

    public DBFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(DBFile profilePicture) {
        this.profilePicture = profilePicture;
    }
}
