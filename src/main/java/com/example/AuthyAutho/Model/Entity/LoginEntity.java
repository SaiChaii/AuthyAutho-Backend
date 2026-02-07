package com.example.AuthyAutho.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users") // Maps this class to a table named 'users' in PostgreSQL
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // Useful for creating user objects in your service layer
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID in Postgres
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Loads roles immediately with the user
    private Set<String> roles;
}
