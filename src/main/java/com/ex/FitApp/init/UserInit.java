package com.ex.FitApp.init;

import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.enums.BodyGroup;
import com.ex.FitApp.models.entities.enums.BodyType;
import com.ex.FitApp.repositories.ExerciseRepository;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.AuthorityRepository;
import com.ex.FitApp.repositories.WorkoutRepository;
import com.ex.FitApp.services.AuthorityProcessingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final AuthorityProcessingService authorityProcessingService;

    public UserInit(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, AuthorityProcessingService authorityProcessingService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.authorityProcessingService = authorityProcessingService;
    }

    @Override
    public void run(String... args){
        if(this.authorityRepository.count() == 0){
            this.authorityProcessingService.seedAuthorities();
        }
        if(this.userRepository.count()==0){

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
            admin.getAuthorities().add(this.authorityRepository.findByAuthority("ROLE_ROOT_ADMIN"));

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
            user.getAuthorities().add(this.authorityRepository.findByAuthority("ROLE_USER"));

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
