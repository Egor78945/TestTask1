package com.example.testtask1.controller.measurement;

import com.example.testtask1.configuration.security.WebSecurityConfiguration;
import com.example.testtask1.configuration.security.jwt.JWTConfiguration;
import com.example.testtask1.model.dto.MeasurementModel;
import com.example.testtask1.service.measurement.MeasurementService;
import com.example.testtask1.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "SERVER.PORT=8080")
@WebMvcTest(MeasurementController.class)
@Import(WebSecurityConfiguration.class)
public class MeasurementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private JWTConfiguration jwtConfiguration;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private MeasurementService measurementService;

    @Test
    @WithMockUser
    public void deniedMeasurementRegistration() throws Exception {
        MeasurementService measurementService = mock(MeasurementService.class);

        MeasurementModel measurementModel = new MeasurementModel();
        JsonMapper jsonMapper = new JsonMapper();

        Mockito.doThrow(new RuntimeException()).when(measurementService);
        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/add").content(jsonMapper.writeValueAsString(measurementModel)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
    }

    @Test
    @WithMockUser
    public void successfullyGetAllMeasurements() throws Exception {
        Mockito.when(measurementService.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/measurements")).andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    public void deniedGetAllMeasurements() throws Exception {
        Mockito.when(measurementService.getAll()).thenThrow(new RuntimeException());
        mockMvc.perform(MockMvcRequestBuilders.get("/measurements")).andExpect(status().is(400));
    }

    @Test
    @WithMockUser
    public void successfullyGetRainingCount() throws Exception {
        Mockito.when(measurementService.getRainingCount()).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/measurements/rainyDaysCount")).andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    public void deniedGetRainingCount() throws Exception {
        Mockito.when(measurementService.getRainingCount()).thenThrow(new RuntimeException());
        mockMvc.perform(MockMvcRequestBuilders.get("/measurements/rainyDaysCount")).andExpect(status().is(400));
    }

    @Test
    public void deniedAccess() throws Exception {
        Mockito.when(measurementService.getRainingCount()).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/measurements/rainyDaysCount")).andExpect(status().is(401));
    }
}
