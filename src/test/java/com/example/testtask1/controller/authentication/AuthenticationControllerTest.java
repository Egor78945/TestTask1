package com.example.testtask1.controller.authentication;

import com.example.testtask1.configuration.security.WebSecurityConfiguration;
import com.example.testtask1.configuration.security.jwt.JWTConfiguration;
import com.example.testtask1.model.dto.AuthenticationModel;
import com.example.testtask1.service.authentication.AuthenticationService;
import com.example.testtask1.service.user.UserService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "SERVER.PORT=8080")
@WebMvcTest(AuthenticationController.class)
@Import(WebSecurityConfiguration.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private JWTConfiguration jwtConfiguration;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private AuthenticationService authenticationService;


    @Test
    public void successfullyAuthentication() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();
        AuthenticationModel authenticationModel = new AuthenticationModel();
        Mockito.when(authenticationService.authenticate(authenticationModel))
                .thenReturn("jwt");

        mockMvc.perform(MockMvcRequestBuilders.get("/auth").content(jsonMapper.writeValueAsString(jsonMapper)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deniedAuthentication() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();
        AuthenticationModel authenticationModel = new AuthenticationModel();
        Mockito.when(authenticationService.authenticate(authenticationModel))
                .thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/auth").content(jsonMapper.writeValueAsString(authenticationModel)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    @Test
    public void successfullyRegistration() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setEmail("email12345@gmail.com");
        authenticationModel.setPassword("123456password");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth").content(jsonMapper.writeValueAsString(authenticationModel)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deniedRegistration() throws Exception {
        AuthenticationService mock = mock(AuthenticationService.class);
        JsonMapper jsonMapper = new JsonMapper();
        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setEmail("email12345@gmail.com");
        authenticationModel.setPassword("123456password");
        Mockito.doThrow(new RuntimeException()).when(mock);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth").content(jsonMapper.writeValueAsString(authenticationModel)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }
}
