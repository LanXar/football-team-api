package com.example.footballteamapi.model;

import jakarta.persistence.*;
import java.util.List;

// Represents a football team entity.
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String acronym;
    private Double budget;

    // Association to players, with cascade operations to handle player lifecycle
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    // Default constructor required by JPA.
    public Team() {
    }

    // Constructs a new team with the specified details.
    public Team(Long id, String name, String acronym, Double budget) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.budget = budget;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
