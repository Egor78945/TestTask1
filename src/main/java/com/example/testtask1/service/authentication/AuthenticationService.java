package com.example.testtask1.service.authentication;

import com.example.testtask1.configuration.security.jwt.JWTConfiguration;
import com.example.testtask1.enumeration.role.Roles;
import com.example.testtask1.model.dto.AuthenticationModel;
import com.example.testtask1.model.entity.Role;
import com.example.testtask1.model.entity.User;
import com.example.testtask1.service.role.RoleService;
import com.example.testtask1.service.user.UserService;
import com.example.testtask1.service.user.validator.UserValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Tag(name = "Authentication Service", description = "Provides registration and authentication")
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public String authenticate(AuthenticationModel requestModel) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtConfiguration.generateToken(authentication);
    }

    public void register(AuthenticationModel authenticationModel) {
        if (UserValidator.isValidAuthenticationModel(authenticationModel)) {
            if (!userService.existsUserByEmail(authenticationModel.getEmail())) {
                User user = new User(authenticationModel.getEmail(), passwordEncoder.encode(authenticationModel.getPassword()));
                user = userService.saveUser(user);
                List<Role> roles = List.of(new Role(user.getId(), Roles.DEFAULT.name()));
                roleService.save(roles);
            } else {
                throw new RuntimeException(String.format("User with email %s already exists.", authenticationModel.getEmail()));
            }
        }
    }
}
