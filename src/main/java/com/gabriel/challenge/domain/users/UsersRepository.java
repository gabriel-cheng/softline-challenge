package com.gabriel.challenge.domain.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);
}
