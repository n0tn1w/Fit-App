package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
    Optional<WorkoutEntity> findByWorkoutName(String workoutName);
}
