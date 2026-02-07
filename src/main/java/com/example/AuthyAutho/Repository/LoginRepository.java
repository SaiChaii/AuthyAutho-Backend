package com.example.AuthyAutho.Repository;

import com.example.AuthyAutho.Model.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    // Custom query method: Spring derives the SQL from the method name
    // Translates to: SELECT * FROM users WHERE username = ?
    Optional<LoginEntity> findByUsername(String username);
}
