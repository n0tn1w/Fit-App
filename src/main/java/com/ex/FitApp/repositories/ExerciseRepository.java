package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {

    Optional<ExerciseEntity> findByExName(String name);

    @Query("SELECT ex.exName FROM ExerciseEntity ex ")
    List<String> getAllByName();
}
