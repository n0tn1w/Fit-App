package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
    Optional<WorkoutEntity> findByWorkoutName(String workoutName);

    @Query("SELECT work.exercises FROM WorkoutEntity work WHERE work.id=?1")
    List<ExerciseEntity> findAllExerciseFromWorkoutId(Long workoutId);
}
