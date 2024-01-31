package com.mindera.HelloMam.repositories;

import com.mindera.HelloMam.dtos.gets.UserGetDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindera.HelloMam.entities.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserGetDto> findByEmail(String email);
    Optional<UserGetDto> findByUsername(String username);
    Optional<UserGetDto> findByName(String name);
}