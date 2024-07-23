package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<Page<Team>> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Team> teams = teamService.getAllTeams(pageable);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(teams);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdTeam);
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<Page<Player>> getPlayersByTeamId(@PathVariable Long teamId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Player> players = teamService.getPlayersByTeamId(teamId, pageable);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(players);
    }

    @PostMapping("/{teamId}/players")
    public ResponseEntity<Player> addPlayerToTeam(@PathVariable Long teamId, @RequestBody Player player) {
        Player addedPlayer = teamService.addPlayerToTeam(teamId, player);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(addedPlayer);
    }
}