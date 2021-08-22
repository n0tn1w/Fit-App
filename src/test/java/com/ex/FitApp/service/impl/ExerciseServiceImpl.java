package com.ex.FitApp.service.impl;

import com.ex.FitApp.models.bindings.ExerciseAddBinding;
import com.ex.FitApp.models.entities.ExerciseEntity;
import com.ex.FitApp.models.entities.enums.BodyGroup;
import com.ex.FitApp.repositories.ExerciseRepository;
import com.ex.FitApp.services.ExerciseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceImpl {

    private final Long VALID_ID = 1L;
    private final String VALID_EX_NAME = "validExName";
    private final int VALID_SETS = 10;
    private final double VALID_WEIGHTS = 10.0;
    private final BodyGroup VALID_BODY_GROUP= BodyGroup.ARMS;
    private final String VALID_IMAGE_URL = "imageulr";
    private final String VALID_DESCRIPTION = "very good ex";

    //    private String exName;
    //    private int sets;
    //    private double weights;
    //    private BodyGroup bodyGroup;
    //    private String imageUrl;
    //    private String description;

    private ExerciseEntity exerciseEntity;
    private ModelMapper modelMapper;
    private ExerciseService serviceToTest;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ExerciseRepository mockExerciseRepository;

    @BeforeEach
    void setUp(){
        this.serviceToTest=new com.ex.FitApp.services.impl.ExerciseServiceImpl(mockModelMapper, mockExerciseRepository);
        this.modelMapper=mockModelMapper;

        this.exerciseEntity=new ExerciseEntity();
        exerciseEntity.setId(VALID_ID);
        exerciseEntity.setExName(VALID_EX_NAME);
        exerciseEntity.setSets(VALID_SETS);
        exerciseEntity.setWeights(VALID_WEIGHTS);
        exerciseEntity.setBodyGroup(VALID_BODY_GROUP);
        exerciseEntity.setImageUrl(VALID_IMAGE_URL);
        exerciseEntity.setDescription(VALID_DESCRIPTION);
    }

    @Test
    public void addTest(){

        this.serviceToTest.addExercise(this.mockModelMapper.map(exerciseEntity, ExerciseAddBinding.class));

        assertNotNull(exerciseEntity);
    }

}
