package com.almuneer.portal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, length = 20)
    private String role;
}
