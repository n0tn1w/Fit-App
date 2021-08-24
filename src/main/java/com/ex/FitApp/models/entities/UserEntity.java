package com.ex.FitApp.models.entities;

import com.ex.FitApp.models.entities.enums.BodyType;
import org.apache.catalina.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
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
    private List<UserRoleEntity> roles=new ArrayList<>();

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

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(UserRoleEntity roleEntity) {
        this.roles.add(roleEntity);
        return this;

    }

    public Set<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }
}
