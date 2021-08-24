package com.ex.FitApp.services;


import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.UserAboutViewModel;
import com.ex.FitApp.models.views.UserControlPanelView;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    void setWorkout(String username, WorkoutEntity workout);

    List<UserControlPanelView> getAllUsersDetails();
}
