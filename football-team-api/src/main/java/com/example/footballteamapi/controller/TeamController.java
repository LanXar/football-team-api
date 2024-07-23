package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.service.TeamService;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Get all teams
    @GetMapping
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamService.getAllTeams(pageable);
    }
    
    // Get team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
    Optional<Team> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdTeam);
    }

    // Get players by team ID
    @GetMapping("/{teamId}/players")
    public ResponseEntity<Page<Player>> getPlayersByTeamId(@PathVariable Long teamId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Player> players = teamService.getPlayersByTeamId(teamId, pageable);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(players);
    }

    // Add player to team
    @PostMapping("/{teamId}/players")
    public ResponseEntity<Player> addPlayerToTeam(@PathVariable Long teamId, @RequestBody Player player) {
        Player addedPlayer = teamService.addPlayerToTeam(teamId, player);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(addedPlayer);
    }

    // Update an existing team
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) {
        try {
            Team updatedTeam = teamService.updateTeam(id, teamDetails);
            return ResponseEntity.ok(updatedTeam);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a team by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}