package com.ex.FitApp.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "username", password = "pass", roles = "USER")
    @Test
    public void getExercisesShouldRedirectToLoginPage() throws Exception {
        this.mockMvc.perform(get("/exercise/add").secure(true))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
