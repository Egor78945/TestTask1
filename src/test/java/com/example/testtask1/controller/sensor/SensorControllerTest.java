package com.example.testtask1.controller.sensor;

import com.example.testtask1.configuration.security.WebSecurityConfiguration;
import com.example.testtask1.configuration.security.jwt.JWTConfiguration;
import com.example.testtask1.model.dto.MeasurementModel;
import com.example.testtask1.model.dto.SensorModel;
import com.example.testtask1.service.measurement.MeasurementService;
import com.example.testtask1.service.sensor.SensorService;
import com.example.testtask1.service.user.UserService;
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

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "SERVER.PORT=8080")
@WebMvcTest(SensorController.class)
@Import(WebSecurityConfiguration.class)
public class SensorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private JWTConfiguration jwtConfiguration;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private SensorService sensorService;

    @Test
    @WithMockUser
    public void deniedSensorRegistration() throws Exception {
        SensorService sensorService = mock(SensorService.class);

        SensorModel sensorModel = new SensorModel();
        JsonMapper jsonMapper = new JsonMapper();

        Mockito.doThrow(new RuntimeException()).when(sensorService);
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors/registration").content(jsonMapper.writeValueAsString(sensorModel)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
    }
}
