package com.example.testtask1.controller.authentication;

import com.example.testtask1.model.dto.AuthenticationModel;
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
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationModel authenticationModel) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationModel));
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody AuthenticationModel authenticationModel){
        authenticationService.register(authenticationModel);
        return ResponseEntity.ok("User has been registered");
    }
}
