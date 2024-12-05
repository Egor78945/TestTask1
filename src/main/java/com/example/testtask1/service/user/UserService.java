package com.example.testtask1.service.user;

import com.example.testtask1.model.entity.Role;
import com.example.testtask1.model.entity.User;
import com.example.testtask1.model.security.UserDetailsImplementation;
import com.example.testtask1.repository.RoleRepository;
import com.example.testtask1.repository.UserRepository;
import com.example.testtask1.service.role.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Tag(name = "User Service", description = "Provides manipulations with users")
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByEmail(username);
        List<String> roles = roleService.getUserRoles(user.getId())
                .stream()
                .map(Role::getRole)
                .toList();
        return new UserDetailsImplementation(user.getEmail(), user.getPassword(), roles);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException(String.format("User with email %s is not found.", email)));
    }

    public Boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    public User saveUser(User user){
       return userRepository.save(user);
    }
}
