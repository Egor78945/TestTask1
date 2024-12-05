package com.example.testtask1.controller.authentication;

import com.example.testtask1.annotation.exception.handler.AuthenticationControllerExceptionHandler;
import com.example.testtask1.model.dto.AuthenticationModel;
import com.example.testtask1.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@AuthenticationControllerExceptionHandler
@Tag(name = "Authentication API")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate a user", description = "Authenticating a registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
            @ApiResponse(responseCode = "401", description = "User is not registered, either email or password in invalid")
    })
    @GetMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationModel authenticationModel) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationModel));
    }

    @Operation(summary = "Register a user", description = "Registering a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registration"),
            @ApiResponse(responseCode = "401", description = "Email or password in wrong format or email is already busy by other user")
    })
    @PostMapping
    public ResponseEntity<String> register(@RequestBody AuthenticationModel authenticationModel){
        authenticationService.register(authenticationModel);
        return ResponseEntity.ok("User has been registered");
    }
}
