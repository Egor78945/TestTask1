package com.example.testtask1.controller.authentication;

import com.example.testtask1.model.dto.AuthenticationRequestModel;
import com.example.testtask1.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestModel authenticationRequestModel) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestModel));
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody AuthenticationRequestModel authenticationRequestModel){
        authenticationService.register(authenticationRequestModel);
        return ResponseEntity.ok("User has been registered");
    }
}
