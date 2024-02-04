package com.nihat.flightsearchapi.repositories;

import com.nihat.flightsearchapi.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Long> {
//    @EntityGraph(attributePaths = "userRoles")
    Optional<AppUser> findByUsername(String username);
}
