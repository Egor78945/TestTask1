package com.example.testtask1.repository;

import com.example.testtask1.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<List<Role>> findAllByUserId(Long userId);
}
