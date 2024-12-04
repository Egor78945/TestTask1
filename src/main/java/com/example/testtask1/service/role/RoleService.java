package com.example.testtask1.service.role;

import com.example.testtask1.model.entity.Role;
import com.example.testtask1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getUserRoles(Long id) {
        return roleRepository.findAllByUserId(id).orElseThrow(() -> new RuntimeException(String.format("Role with userId %s is not found.", id)));
    }

    public void save(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
