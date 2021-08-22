package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.UserRoleEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import com.ex.FitApp.models.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByRoles(UserRoleEntity userRole);

    @Query("SELECT us.workouts FROM UserEntity us WHERE us.username=?1")
    List<WorkoutEntity> findAllUsersWorkout(String username);
}
