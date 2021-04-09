package com.vahan.blok.rest.repository;

import com.vahan.blok.rest.model.User;
import com.vahan.blok.rest.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String s);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByRole(Role role);

    boolean existsByEmail(String email);

}
