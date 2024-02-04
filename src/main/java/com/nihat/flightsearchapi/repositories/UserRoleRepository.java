package com.nihat.flightsearchapi.repositories;


import com.nihat.flightsearchapi.entities.Role;
import com.nihat.flightsearchapi.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findUserRoleByRole(Role role);
}
