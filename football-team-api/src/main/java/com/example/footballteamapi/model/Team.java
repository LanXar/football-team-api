// src/main/java/com/example/footballteamapi/model/Team.java
package com.example.footballteamapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String acronym;
    private Double budget;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    // Getters and Setters
}
