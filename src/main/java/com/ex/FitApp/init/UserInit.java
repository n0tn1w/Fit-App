package com.ex.FitApp.init;

import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.UserRoleEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.entities.enums.BodyGroup;
import com.ex.FitApp.models.entities.enums.BodyType;
import com.ex.FitApp.models.entities.enums.UserRole;
import com.ex.FitApp.repositories.ExerciseRepository;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.UserRoleRepository;
import com.ex.FitApp.repositories.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public UserInit(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.userRepository.count()==0){
            UserRoleEntity adminRole= new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole= new UserRoleEntity().setRole(UserRole.USER);
            
            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(userRole);

            UserEntity admin= new UserEntity();
            admin.setFirstName("root");
            admin.setLastName("root");
            admin.setBodyType(BodyType.ECTOMORPH);
            admin.setUsername("root");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setAge(20);
            admin.setEmail("root@root");
            admin.setHeight(1.8);
            admin.setWeight(70);
            admin.getRoles().add(this.userRoleRepository.findByRole(UserRole.ADMIN));

            this.userRepository.save(admin);

            UserEntity user= new UserEntity();
            user.setFirstName("Kiril");
            user.setLastName("Kirilov");
            user.setBodyType(BodyType.MESOMORPH);
            user.setUsername("ivan");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setAge(16);
            user.setEmail("ivan@ivan");
            user.setHeight(1.74);
            user.setWeight(90);
            user.getRoles().add(this.userRoleRepository.findByRole(UserRole.USER));

            this.userRepository.save(user);

            ExerciseEntity exercise1=new ExerciseEntity();
            exercise1.setBodyGroup(BodyGroup.ABDOMINAL);
            exercise1.setDescription("Workout your abs");
            exercise1.setExName("Plank");
            exercise1.setImageUrl("plankimage");
            exercise1.setSets(3);
            exercise1.setWeights(10);

            this.exerciseRepository.save(exercise1);

            ExerciseEntity exercise2=new ExerciseEntity();
            exercise2.setBodyGroup(BodyGroup.LEGS);
            exercise2.setDescription("Workout your legs");
            exercise2.setExName("Squat");
            exercise2.setImageUrl("squatstockimage");
            exercise2.setSets(4);
            exercise2.setWeights(70);

            this.exerciseRepository.save(exercise2);


        }
    }
}
