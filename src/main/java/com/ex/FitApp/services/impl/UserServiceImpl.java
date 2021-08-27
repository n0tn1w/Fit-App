package com.ex.FitApp.services.impl;

import com.ex.FitApp.file.exception.FileStorageException;
import com.ex.FitApp.file.model.DBFile;
import com.ex.FitApp.file.service.DBFileStorageService;
import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.models.bindings.UserUsernameUpdateBinding;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.views.UserControlPanelView;
import com.ex.FitApp.models.views.UserProfileView;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.AuthorityRepository;
import com.ex.FitApp.security.DBUserService;
import com.ex.FitApp.services.AuthorityProcessingService;
import com.ex.FitApp.services.UserService;
import com.ex.FitApp.services.WorkoutService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    private final WorkoutService workoutService;
    private final AuthorityProcessingService authorityProcessingService;

    private final DBUserService dbUserService;
    private final DBFileStorageService fileStorageService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, WorkoutService workoutService, AuthorityProcessingService authorityProcessingService, DBUserService dbUserService, DBFileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.workoutService = workoutService;
        this.authorityProcessingService = authorityProcessingService;
        this.dbUserService = dbUserService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository
                .findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email).orElse(null);
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel registerModel) {
        UserEntity user = this.modelMapper.map(registerModel, UserEntity.class);
        user.setPassword(this.passwordEncoder.encode(registerModel.getPassword()));
        user.setAuthorities(Set.of(authorityProcessingService.getUserAuthority()));
        this.userRepository.saveAndFlush(user);

        UserDetails principal = dbUserService.loadUserByUsername(user.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

    @Transactional
    @Override
    public void setWorkout(String username, WorkoutEntity workoutEntity) {
        UserEntity userEntity=this.userRepository.findByUsername(username).orElse(null);

        if(userEntity.getWorkouts() == null){
            userEntity.setWorkouts(new HashSet<>());
        }
        userEntity.getWorkouts().add(workoutEntity);
        this.userRepository.save(userEntity);
    }

    @Override
    public List<UserControlPanelView> getAllUsersDetails() {
        return this.userRepository.findAllByAuthorities(this.authorityRepository.findByAuthority("ROLE_USER")).stream()
                .map(userEntity -> modelMapper.map(userEntity, UserControlPanelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean uploadProfilePicture(String name, MultipartFile file) throws FileStorageException {
        UserEntity user = this.userRepository.findByUsername(name).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + name + " cannot be found");
        }

        if (file != null) {
            DBFile dbFile = this.fileStorageService.storeFile(file);
            String oldPictureId = null;
            if (user.getProfilePicture() != null) {
                oldPictureId = user.getProfilePicture().getId();
            }
            user.setProfilePicture(dbFile);
            this.userRepository.saveAndFlush(user);
            // Deleting old profile picture if it exists (cleans up files)
            if (oldPictureId != null) {

                this.fileStorageService.deleteFile(oldPictureId);
            }

            return true;
        }
        return false;
    }

    @Override
    public UserProfileView getUserProfile(String username) {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User with username " + username + " cannot be found");
        }
        UserProfileView userProfileViewModel = this.modelMapper.map(
                userEntity,
                UserProfileView.class
        );
        DBFile pictureFile = userEntity.getProfilePicture();
        byte[] profilePicture = null;
        if (pictureFile != null) {
            profilePicture = userEntity.getProfilePicture().getData();
        }
        String profilePictureString = "";
        if (profilePicture != null) {
            profilePictureString = Base64.getEncoder().encodeToString(profilePicture);
        }
        userProfileViewModel.setProfilePictureString(profilePictureString);
        return userProfileViewModel;
    }

    @Override
    public void updateUsername(String username, UserUsernameUpdateBinding user) {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User with username " + username + " cannot be found");
        }

        userEntity.setUsername(user.getUsername());
        this.userRepository.save(userEntity);
//        return userEntity;
    }


}
