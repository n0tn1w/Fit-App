package com.ex.FitApp.services;


import com.ex.FitApp.file.exception.FileStorageException;
import com.ex.FitApp.models.bindings.UserEditBinding;
import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.models.bindings.UserUsernameUpdateBinding;
import com.ex.FitApp.models.bindings.WorkoutAddBinding;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.UserAboutViewModel;
import com.ex.FitApp.models.views.UserControlPanelView;
import com.ex.FitApp.models.views.UserEditView;
import com.ex.FitApp.models.views.UserProfileView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    void setWorkout(String username, WorkoutEntity workout);

    List<UserControlPanelView> getAllUsersDetails();

    boolean uploadProfilePicture(String name, MultipartFile file) throws FileStorageException;

    UserProfileView getUserProfile(String username);

    void updateUsername(String username, UserUsernameUpdateBinding user);

    UserEditView getUserEditView(String name);

    UserEditBinding preSetBindingValue(UserEditView userEditView);

    void updateUserParams(String username, UserEditBinding user);
}
