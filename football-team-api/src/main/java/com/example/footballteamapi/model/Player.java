package com.example.footballteamapi.model;

import jakarta.persistence.*;

// Represents a player entity in the context of a football team.
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

    // Links back to the team a player belongs to
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Default constructor required by JPA.
    public Player() {
    }

    // Constructs a new player with the specified details.
    public Player(Long id, String name, String position, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.team = team;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
