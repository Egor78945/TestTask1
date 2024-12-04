package com.example.testtask1.service.authentication;

import com.example.testtask1.configuration.security.jwt.JWTConfiguration;
import com.example.testtask1.enumeration.role.Roles;
import com.example.testtask1.model.dto.AuthenticationRequestModel;
import com.example.testtask1.model.entity.Role;
import com.example.testtask1.model.entity.User;
import com.example.testtask1.service.role.RoleService;
import com.example.testtask1.service.user.UserService;
import com.example.testtask1.service.user.validator.UserValidator;
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
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTConfiguration jwtConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public String authenticate(AuthenticationRequestModel requestModel) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtConfiguration.generateToken(authentication);
    }

    public void register(AuthenticationRequestModel authenticationRequestModel) {
        if(UserValidator.isValidAuthenticationModel(authenticationRequestModel)){
            User user = new User(authenticationRequestModel.getEmail(), passwordEncoder.encode(authenticationRequestModel.getPassword()));
            user = userService.saveUser(user);
            List<Role> roles = List.of(new Role(user.getId(), Roles.DEFAULT.name()));
            roleService.save(roles);
        }
    }
}
