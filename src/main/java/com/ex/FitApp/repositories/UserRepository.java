package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.AuthorityEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByAuthorities(AuthorityEntity authority);

    @Query("SELECT us.workouts FROM UserEntity us WHERE us.username=?1")
    List<WorkoutEntity> findAllUsersWorkout(String username);
}
