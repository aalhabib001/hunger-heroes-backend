package com.hungerheroes.backend.jwt.repository;


import com.hungerheroes.backend.jwt.model.Role;
import com.hungerheroes.backend.jwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Optional<Role> findByName(RoleName roleName);
    Optional<Role> findByName(RoleName role);
}